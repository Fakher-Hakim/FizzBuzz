package com.fakher.business.repository

import com.fakher.business.model.FormEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FormRepository {

    fun addForm(form: FormEntity): Single<Long>

    fun getAllForms(): Single<List<FormEntity>>

    fun getFormById(int1: Int, int2: Int, str1: String, str2: String): Single<FormEntity>
}