package com.nlpit.redux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.nlpit.redux.databinding.ActivityMainBinding
import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.CounterActions
import timber.log.Timber
import java.lang.Exception

class MainViewModel {

    val counterState: ObservableField<CounterState> = ObservableField()

    init {
        DI.store.dispatch(action = CounterActions.Init)

        DI.store.subscribe { state ->
            if(state.counterState != counterState.get()) {
                Timber.d("Redux > Reciever > ${state.counterState}")
                counterState.set(state.counterState)
            }
        }
    }

    fun decrementButtonOnClick(view: View) {
        DI.store.dispatch(action = CounterActions.Decrement)
    }

    fun incrementButtonOnClick(view: View) {
        DI.store.dispatch(action = CounterActions.Increment)
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel()

        DI.store.dispatch(CounterActions.GeneralError(Exception("Test Error")))
    }
}
