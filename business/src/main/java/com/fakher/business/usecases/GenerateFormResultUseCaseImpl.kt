package com.fakher.business.usecases

import io.reactivex.Single

class GenerateFormResultUseCaseImpl : GenerateFormResultUseCase {

    override fun execute(
        int1: Int,
        int2: Int,
        str1: String,
        str2: String,
        limit: Int
    ): Single<String> {
        var result = ""
        for (i in 1..limit) {
            result += when {
                i % int1 == 0 && i % int2 == 0 -> {
                    "$str1$str2,"
                }
                i % int1 == 0 -> {
                    "$str1,"
                }
                i % int2 == 0 -> {
                    "$str2,"
                }
                else -> {
                    "$i,"
                }
            }
        }
        return Single.just(result.substringBeforeLast(","))
    }
}