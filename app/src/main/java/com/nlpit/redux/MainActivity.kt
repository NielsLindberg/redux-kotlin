package com.nlpit.redux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import androidx.ui.geometry.Size
import com.nlpit.redux.redux.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())

        setContent {
            ReduxApp(DI.store.state)
        }

//        val binding: ActivityMainBinding =
//            DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.viewModel = MainViewModel()
//
//        DI.store.dispatch(CounterActions.GeneralError(Exception("Test Error")))
    }
}
