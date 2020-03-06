package com.nlpit.redux

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import com.nlpit.redux.databinding.ActivityMainBinding
import com.nlpit.redux.redux.CounterActions
import com.nlpit.redux.redux.DI
import java.util.*


class MainViewModel {

    val counterText = ObservableField("")

    init {
        DI.store.dispatch(action = CounterActions.Init)

        DI.store.add {
            counterText.set(it.value.toString())
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
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel()
    }
}
