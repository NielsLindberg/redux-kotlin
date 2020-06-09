package com.nlpit.redux.redux

interface State

data class CounterState(
    val value: Int = 0
) : State {
    val string: String get() = value.toString()
}

data class AppState(
    val counterState: CounterState,
    val errorState: ErrorState? = null,
    val screenState: ScreenState
) : State

data class ErrorState(val message: String?) : State

data class ScreenState(
    val currentScreen: Screen = Screen.Home
)

sealed class Screen {
    object Home: Screen()
    object Lol: Screen()
    object Yo: Screen()
}
