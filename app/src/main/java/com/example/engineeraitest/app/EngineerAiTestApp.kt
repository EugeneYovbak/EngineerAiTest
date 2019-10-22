package com.example.engineeraitest.app

import android.app.Application
import com.example.engineeraitest.app.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EngineerAiTestApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EngineerAiTestApp)
            modules(appModules)
        }
    }
}