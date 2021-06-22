package com.fakher.fizzbuzz.di

import com.fakher.business.repository.FormRepository
import com.fakher.business.usecases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FormInputModule {

    @Provides
    @Singleton
    fun providesGenerateFormResultUseCase(): GenerateFormResultUseCase =
        GenerateFormResultUseCaseImpl()

    @Provides
    @Singleton
    fun providesStoreFormUseCase(formRepository: FormRepository): StoreFormUseCase =
        StoreFormUseCaseImpl(formRepository)

    @Provides
    @Singleton
    fun providesGetFormByIdUseCase(formRepository: FormRepository): GetFormByIdUseCase =
        GetFormByIdUseCaseImpl(formRepository)
}