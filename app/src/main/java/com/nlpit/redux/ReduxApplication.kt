package com.nlpit.redux

import android.app.Application

class ReduxApplication : Application() {

    // AppContainer instance used by the rest of classes to obtain dependencies

    override fun onCreate() {
        super.onCreate()
    }
}