package com.fakher.repository.source.local

import com.fakher.repository.database.FormDAO
import com.fakher.repository.model.FormPersistence
import com.fakher.repository.source.FormDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FormDataSourceLocalImpl @Inject constructor(
    private val formDAO: FormDAO
) : FormDataSource {

    override fun addForm(form: FormPersistence): Single<Long> =
        formDAO.insertForm(form)

    override fun getAllForms(): Single<List<FormPersistence>> =
        formDAO.getAllForms()

    override fun getFormById(int1: Int, int2: Int, str1: String, str2: String) =
        formDAO.getFormById(int1, int2, str1, str2)
}