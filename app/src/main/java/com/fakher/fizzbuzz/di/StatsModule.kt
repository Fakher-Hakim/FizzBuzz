package com.fakher.fizzbuzz.di

import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import com.fakher.business.usecases.GetStatsUseCase
import com.fakher.business.usecases.GetStatsUseCaseImpl
import com.fakher.common.Mapper
import com.fakher.presentation.mapper.FormMapper
import com.fakher.presentation.model.Form
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StatsModule {

    @Provides
    @Singleton
    fun providesMapper(): Mapper<Form, FormEntity> = FormMapper()

    @Provides
    @Singleton
    fun providesGetStatsUseCase(formRepository: FormRepository): GetStatsUseCase =
        GetStatsUseCaseImpl(formRepository)
}