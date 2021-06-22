package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import io.reactivex.Single

interface GetFormByIdUseCase {

    fun execute(int1: Int, int2: Int, str1: String, str2: String): Single<FormEntity>
}