package com.example.upaxtest

import android.app.Application
import com.example.upaxtest.di.ApplicationComponent
import com.example.upaxtest.di.DaggerApplicationComponent

class MyApplication: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.factory().create(this)
}