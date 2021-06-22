package com.fakher.presentation.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakher.business.model.FormEntity
import com.fakher.business.usecases.GenerateFormResultUseCase
import com.fakher.business.usecases.GetFormByIdUseCase
import com.fakher.business.usecases.StoreFormUseCase
import com.fakher.common.extensions.plusAssign
import com.fakher.common.extensions.runInBackground
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FormInputViewModel @Inject constructor(
    private val generateFormResult: GenerateFormResultUseCase,
    private val storeFormUseCase: StoreFormUseCase,
    private val getFormByIdUseCase: GetFormByIdUseCase
) : ViewModel() {

    private val TAG = FormInputViewModel::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val _formResult = MutableLiveData("")
    val formResult: LiveData<String>
        get() = _formResult
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _error = MutableLiveData("")
    val error: LiveData<String>
        get() = _error

    fun generateFormResult(int1: String, int2: String, str1: String, str2: String, limit: String) {
        isLoading(true)
        _error.value = ""
        _formResult.value = ""
        try {
            if (isInputValid(int1.toInt(), int2.toInt(), limit.toInt(), str1, str2)) {
                compositeDisposable += generateFormResult.execute(
                    int1.toInt(),
                    int2.toInt(),
                    str1,
                    str2,
                    limit.toInt()
                )
                    .runInBackground()
                    .subscribe({ result ->
                        _formResult.value = result
                        isLoading(false)
                        storeForm(int1.toInt(), int2.toInt(), str1, str2)
                    }, { error ->
                        _error.value = error.localizedMessage
                        isLoading(false)
                    })
            } else {
                _error.value = "Inputs are invalid !!"
                isLoading(false)
            }
        } catch (e: NumberFormatException) {
            _error.value = "Not valid numbers !!"
            isLoading(false)
        }
    }

    private fun storeForm(int1: Int, int2: Int, str1: String, str2: String) {
        compositeDisposable += getFormByIdUseCase.execute(int1, int2, str1, str2)
            .map { formEntity ->
                storeFormUseCase.execute(formEntity.copy(hits = formEntity.hits + 1))
                    .subscribe()
            }
            .doOnError {
                storeFormUseCase.execute(FormEntity(int1, int2, str1, str2, 1))
                    .subscribe()
            }
            .runInBackground()
            .subscribe({
                Log.d(TAG, "Form stored successfully")
            }, {
                Log.e(TAG, "Error while saving form: ${it.localizedMessage}")
            })
    }

    private fun isInputValid(int1: Int, int2: Int, limit: Int, str1: String, str2: String) =
        limit in 1..100 &&
                limit > int1 &&
                limit > int2 &&
                int1 != int2 &&
                str1 != str2

    private fun isLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}