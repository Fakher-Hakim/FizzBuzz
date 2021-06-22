package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.reactivex.Single
import javax.inject.Inject

class GetStatsUseCaseImpl @Inject constructor(val formRepository: FormRepository) :
    GetStatsUseCase {

    override fun execute(): Single<List<FormEntity>> = formRepository.getAllForms()
}