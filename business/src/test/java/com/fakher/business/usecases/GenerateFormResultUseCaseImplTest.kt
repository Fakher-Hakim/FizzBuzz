package com.fakher.business.usecases

import org.junit.Before
import org.junit.Test

class GenerateFormResultUseCaseImplTest {

    private lateinit var tested: GenerateFormResultUseCase

    @Before
    fun setUp() {
        tested = GenerateFormResultUseCaseImpl()
    }

    @Test
    fun `given valid arguments must generate output`() {
        val int1 = 3
        val int2 = 5
        val str1 = "fizz"
        val str2 = "buzz"
        val limit = 16

        val expected = "1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16"
        val subscriber = tested.execute(int1, int2, str1, str2, limit).test()

        subscriber.assertResult(expected)
    }
}