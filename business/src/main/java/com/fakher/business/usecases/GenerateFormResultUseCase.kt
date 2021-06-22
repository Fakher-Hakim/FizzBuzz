package com.fakher.business.usecases

import io.reactivex.Single

interface GenerateFormResultUseCase {

    fun execute(int1: Int, int2: Int, str1: String, str2: String, limit: Int): Single<String>
}