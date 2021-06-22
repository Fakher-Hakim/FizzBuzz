package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import io.reactivex.Single

interface GetStatsUseCase {

    fun execute(): Single<List<FormEntity>>
}