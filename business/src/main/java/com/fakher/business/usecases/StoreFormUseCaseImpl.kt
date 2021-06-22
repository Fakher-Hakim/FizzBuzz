package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class StoreFormUseCaseImpl @Inject constructor(private val formRepository: FormRepository) :
    StoreFormUseCase {

    override fun execute(form: FormEntity): Single<Long> =
        formRepository.addForm(form)
}