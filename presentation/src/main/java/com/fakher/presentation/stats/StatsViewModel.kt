package com.fakher.presentation.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakher.business.model.FormEntity
import com.fakher.business.usecases.GetStatsUseCase
import com.fakher.common.Mapper
import com.fakher.common.extensions.plusAssign
import com.fakher.common.extensions.runInBackground
import com.fakher.presentation.model.Form
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StatsViewModel @Inject constructor(
    private val mapper: Mapper<Form, FormEntity>,
    private val getStatsUseCase: GetStatsUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _stats = MutableLiveData(listOf<Form>())
    val stats: LiveData<List<Form>>
        get() = _stats
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _error = MutableLiveData("")
    val error: LiveData<String>
        get() = _error

    fun getStats() {
        _isLoading.value = true
        compositeDisposable += getStatsUseCase.execute()
            .map { forms ->
                forms.map { mapper.from(it) }
            }
            .runInBackground()
            .subscribe({ forms ->
                _isLoading.value = false
                _stats.value = forms.sortedByDescending { it.hits }
            }, {
                _isLoading.value = false
                _error.value = it.localizedMessage
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}