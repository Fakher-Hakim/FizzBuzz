package com.fakher.fizzbuzz.di

import com.fakher.presentation.factory.ViewModelFactory
import com.fakher.presentation.form.FormInputViewModel
import com.fakher.presentation.stats.StatsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        FormInputModule::class,
        StatsModule::class
    ]
)
interface ApplicationComponent {

    fun getFormInputVMFactory(): ViewModelFactory<FormInputViewModel>
    fun getStatsVMFactory(): ViewModelFactory<StatsViewModel>
}