package com.fakher.fizzbuzz.di

import android.app.Activity

interface DaggerComponentProvider {

    val component: ApplicationComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).component
