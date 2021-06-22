package com.fakher.repository.repository

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import com.fakher.common.Mapper
import com.fakher.repository.model.FormPersistence
import com.fakher.repository.source.FormDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FormRepositoryImpl @Inject constructor(
    private val formDataSourceLocalImpl: FormDataSource,
    private val mapper: Mapper<FormPersistence, FormEntity>
) : FormRepository {

    override fun addForm(form: FormEntity): Single<Long> =
        formDataSourceLocalImpl.addForm(mapper.from(form))

    override fun getAllForms(): Single<List<FormEntity>> =
        formDataSourceLocalImpl.getAllForms()
            .map { forms ->
                forms.map { mapper.to(it) }
            }

    override fun getFormById(int1: Int, int2: Int, str1: String, str2: String): Single<FormEntity> =
        formDataSourceLocalImpl.getFormById(int1, int2, str1, str2)
            .map { mapper.to(it) }
}