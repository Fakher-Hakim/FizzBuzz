package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetFormByIdUseCaseImplTest {

    private lateinit var tested: GetFormByIdUseCase

    @MockK
    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = GetFormByIdUseCaseImpl(formRepository)
    }

    @Test
    fun `given existing form must return form`() {
        val expected = FormEntity(1, 2, "a", "b", 1)

        every {
            formRepository.getFormById(1, 2, "a", "b")
        } returns Single.just(expected)
        val subscriber = tested.execute(1, 2, "a", "b").test()

        subscriber
            .assertNoErrors()
            .assertComplete()

        subscriber.assertResult(expected)
    }
}