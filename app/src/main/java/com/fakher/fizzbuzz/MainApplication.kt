package com.fakher.fizzbuzz

import android.app.Application
import com.fakher.fizzbuzz.di.ApplicationComponent
import com.fakher.fizzbuzz.di.ApplicationModule
import com.fakher.fizzbuzz.di.DaggerApplicationComponent
import com.fakher.fizzbuzz.di.DaggerComponentProvider

class MainApplication : Application(), DaggerComponentProvider {

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}