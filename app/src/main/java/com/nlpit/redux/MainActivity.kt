package com.nlpit.redux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nlpit.redux.redux.*
import timber.log.Timber
import androidx.ui.core.setContent


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            ReduxApp(DI.store.state)
        }
    }

}
