package com.nlpit.redux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nlpit.redux.redux.*
import timber.log.Timber
import androidx.ui.core.setContent


class MainActivity : AppCompatActivity(), StateSubscriber<AppState> {


    override fun onState(state: AppState) {
        setContent {
            ReduxApp(state)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }

    override fun onResume() {
        DI.store.subscribe(this)
        super.onResume()
    }

    override fun onPause() {
        DI.store.remove(this)
        super.onPause()
    }
}
