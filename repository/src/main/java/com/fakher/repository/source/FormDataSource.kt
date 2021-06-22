package com.fakher.repository.source

import com.fakher.repository.model.FormPersistence
import io.reactivex.Completable
import io.reactivex.Single

interface FormDataSource {

    fun addForm(form: FormPersistence): Single<Long>

    fun getAllForms(): Single<List<FormPersistence>>

    fun getFormById(int1: Int, int2: Int, str1: String, str2: String): Single<FormPersistence>
}