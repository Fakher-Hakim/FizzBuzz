package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFormByIdUseCaseImpl @Inject constructor(private val formRepository: FormRepository) :
    GetFormByIdUseCase {

    override fun execute(int1: Int, int2: Int, str1: String, str2: String): Single<FormEntity> =
        formRepository.getFormById(int1, int2, str1, str2)
}