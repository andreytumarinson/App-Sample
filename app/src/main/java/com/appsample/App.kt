package com.appsample

import android.app.Application
import com.appsample.app.di.AppComponent
import com.appsample.app.di.AppComponentHolder

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppComponentHolder.get().context = applicationContext
    }
}
