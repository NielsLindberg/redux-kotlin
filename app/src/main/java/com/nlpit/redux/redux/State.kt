package com.nlpit.redux.redux

import androidx.compose.Immutable
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import com.nlpit.redux.redux.actions.NavigateActions

@Immutable
data class CounterState(
    val counter: Int = 0,
    val nestedCounterState: NestedCounterState = NestedCounterState()
) {
    val string: String get() = counter.toString()

}

@Immutable
data class NestedCounterState(
    val counter: Int = 0
) {
    val string: String get() = counter.toString()
}

@Immutable
data class AppState(
    val counterState: CounterState,
    val errorState: ErrorState,
    val screenState: ScreenState
)

@Immutable
data class ErrorState(val message: String?)

@Immutable
data class ScreenState(
    val currentScreen: Screen = Screen.Home,
    val screens: List<Screen> = listOf(Screen.Home, Screen.Lol, Screen.Yo)
)

sealed class Screen(val index: Int, val title: String, val action: NavigateActions) {
    object Home : Screen(0, "Home", NavigateActions.HomeScreen)
    object Lol : Screen(1, "Lol", NavigateActions.LolScreen)
    object Yo : Screen(2, "Yo", NavigateActions.YoScreen)
}
