package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import io.reactivex.Completable
import io.reactivex.Single

interface StoreFormUseCase {

    fun execute(form: FormEntity): Single<Long>
}