package com.nlpit.redux.redux

import androidx.compose.MutableState
import com.nlpit.redux.redux.actions.NavigateActions

data class CounterState(
    val counter: Int = 0
) {
    val string: String get() = counter.toString()
}

data class AppState(
    var counterState: MutableState<CounterState>,
    var errorState: MutableState<ErrorState>,
    var screenState: MutableState<ScreenState>
)

data class ErrorState(val message: String?)

data class ScreenState(
    val currentScreen: Screen = Screen.Home,
    val screens: List<Screen> = listOf(Screen.Home, Screen.Lol, Screen.Yo))

sealed class Screen(val index: Int, val title: String, val action: NavigateActions) {
    object Home: Screen(0, "Home", NavigateActions.HomeScreen)
    object Lol: Screen(1, "Lol", NavigateActions.LolScreen)
    object Yo: Screen(2, "Yo", NavigateActions.YoScreen)
}
