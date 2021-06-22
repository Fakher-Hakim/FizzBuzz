package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test

class StoreFormUseCaseImplTest {

    private lateinit var tested: StoreFormUseCase

    @MockK
    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = StoreFormUseCaseImpl(formRepository)
    }

    @Test
    fun `given store form must create form`() {
        val form = FormEntity(1, 2, "a", "b", 1)
        val expected: Long = 1

        every {
            formRepository.addForm(form)
        } returns Single.just(expected)

        val subscriber = tested.execute(form).test()

        subscriber
            .assertNoErrors()
            .assertComplete()

        subscriber.assertResult(expected)
    }
}