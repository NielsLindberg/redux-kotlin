package com.nlpit.redux

import android.app.Application
import timber.log.Timber

class ReduxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}