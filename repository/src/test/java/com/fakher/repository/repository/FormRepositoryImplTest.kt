package com.fakher.repository.repository

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import com.fakher.common.Mapper
import com.fakher.repository.model.FormPersistence
import com.fakher.repository.source.local.FormDataSourceLocalImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class FormRepositoryImplTest {

    @MockK
    lateinit var formDataSourceLocalImpl: FormDataSourceLocalImpl

    @MockK
    lateinit var mapper: Mapper<FormPersistence, FormEntity>
    lateinit var tested: FormRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        tested = FormRepositoryImpl(formDataSourceLocalImpl, mapper)
    }

    @Test
    fun `given form verify it is added to database`() {
        val formPersistence = FormPersistence(1, 2, "a", "b", 8)
        val formEntity = FormEntity(1, 2, "a", "b", 8)

        every { formDataSourceLocalImpl.addForm(formPersistence) } returns (Single.just(1))

        every { mapper.from(formEntity) } returns formPersistence

        val subscriber = tested
            .addForm(FormEntity(1, 2, "a", "b", 8))
            .test()

        subscriber
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `given form id get form from database`() {
        val formPersistence = FormPersistence(1, 2, "a", "b", 8)
        val formEntity = FormEntity(1, 2, "a", "b", 8)

        every { formDataSourceLocalImpl.getFormById(1, 2, "a", "b") }returns
                Single.just(formPersistence)

        every { mapper.from(formEntity) } returns formPersistence

        every { mapper.to(formPersistence) } returns formEntity

        val subscriber = tested
            .getFormById(1, 2, "a", "b")
            .test()

        subscriber
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `given get all form then get all forms from database`() {
        val formPersistence = FormPersistence(1, 2, "a", "b", 8)
        val formEntity = FormEntity(1, 2, "a", "b", 8)

        every { formDataSourceLocalImpl.getAllForms() }returns
                Single.just(listOf(formPersistence))

        every { mapper.from(formEntity) } returns formPersistence

        every { mapper.to(formPersistence) } returns formEntity

        val subscriber = tested
            .getAllForms()
            .test()

        subscriber
            .assertNoErrors()
            .assertComplete()
    }
}