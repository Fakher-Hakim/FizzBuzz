package com.fakher.business.usecases

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetStatsUseCaseImplTest {

    private lateinit var tested: GetStatsUseCase

    @MockK
    lateinit var formRepository: FormRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = GetStatsUseCaseImpl(formRepository)
    }

    @Test
    fun `given fetching stats must return all forms`() {
        val expected = listOf(
            FormEntity(1, 2, "a", "b", 1),
            FormEntity(3, 9, "a", "b", 4)
        )

        every {
            formRepository.getAllForms()
        } returns Single.just(expected)

        val subscriber = tested.execute().test()

        subscriber.assertNoErrors().assertComplete()

        subscriber.assertValue(expected)
    }
}