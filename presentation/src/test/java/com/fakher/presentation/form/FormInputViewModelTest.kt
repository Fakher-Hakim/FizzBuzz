package com.fakher.presentation.form

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fakher.business.model.FormEntity
import com.fakher.business.usecases.GenerateFormResultUseCase
import com.fakher.business.usecases.GetFormByIdUseCase
import com.fakher.business.usecases.StoreFormUseCase
import com.fakher.common.test
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.mockk.MockKAnnotations
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FormInputViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = RxTestUtils().getTestingRule()

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var tested: FormInputViewModel

    var generateFormResult: GenerateFormResultUseCase = mock {
        onGeneric { execute(3, 5, "buzz", "buzz", 16) } doReturn
                Single.just("1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16")
    }

    var storeFormUseCase: StoreFormUseCase = mock {
        onGeneric { execute(FormEntity(3, 5, "buzz", "buzz")) } doReturn
                Single.just(1)
    }

    var getFormByIdUseCase: GetFormByIdUseCase = mock {
        onGeneric { execute(3, 5, "buzz", "buzz") } doReturn
                Single.just(FormEntity(1, 2, "e", "z", 2))
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = FormInputViewModel(generateFormResult, storeFormUseCase, getFormByIdUseCase)
    }

    @Test
    fun `given generate form result with limit over 100 outputs should show error`() {

        tested.generateFormResult("3", "5", "fizz", "buzz", "101")

        tested.error.test().assertValue("Inputs are invalid !!")
    }

    @Test
    fun `given generate form result with int bigger than limit outputs should show error`() {

        tested.generateFormResult("30", "5", "fizz", "buzz", "10")

        tested.error.test().assertValue("Inputs are invalid !!")
    }

    @Test
    fun `given generate form result with identical int outputs should show error`() {

        tested.generateFormResult("5", "5", "fizz", "buzz", "10")

        tested.error.test().assertValue("Inputs are invalid !!")
    }

    @Test
    fun `given generate form result with identical string outputs should show error`() {

        tested.generateFormResult("3", "5", "fizz", "fizz", "10")

        tested.error.test().assertValue("Inputs are invalid !!")
    }
}