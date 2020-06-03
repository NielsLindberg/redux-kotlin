package com.nlpit.redux.redux

import androidx.databinding.BaseObservable
import java.util.*

interface State

data class CounterState(
    val value: Int = 0
) : State {
    val string: String get() = value.toString()
}

data class AppState(
    val counterState: CounterState,
    val errorState: ErrorState? = null
) : State

data class ErrorState(val message: String?) : State
