package com.intuit.cats

import android.app.Application
import com.intuit.cats.di.AppComponent
import com.intuit.cats.di.DaggerAppComponent

class CatApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}