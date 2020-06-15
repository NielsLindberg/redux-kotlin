package com.nlpit.redux.redux

import androidx.compose.MutableState
import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.redux.actions.NavigateActions

interface Reducer<S> {
    fun reduce(state: S, action: Action)
}

object CounterStateReducer : Reducer<MutableState<CounterState>> {
    override fun reduce(state: MutableState<CounterState>, action: Action) {
        when (action) {
            is CounterActions.Increment -> state.apply {
                value = value.copy(counter = value.counter + 1)
            }
            is CounterActions.Decrement -> state.apply {
                value = value.copy(counter = value.counter - 1)
            }
        }
    }

}

object ErrorStateReducer : Reducer<MutableState<ErrorState>> {
    override fun reduce(state: MutableState<ErrorState>, action: Action) {
        when (action) {
            is CounterActions.GeneralError -> state.apply {
                value = ErrorState(message = action.error.message ?: "No Message")
            }
        }
    }
}

object NavigationReducer : Reducer<MutableState<ScreenState>> {
    override fun reduce(state: MutableState<ScreenState>, action: Action) {
        when (action) {
            is NavigateActions.HomeScreen -> state.apply {
                value = ScreenState(currentScreen = Screen.Home)
            }
            is NavigateActions.LolScreen -> state.apply {
                value = ScreenState(currentScreen = Screen.Lol)
            }
            is NavigateActions.YoScreen -> state.apply {
                value = ScreenState(currentScreen = Screen.Yo)
            }
        }
    }
}

object AppStateReducer : Reducer<AppState> {
    override fun reduce(state: AppState, action: Action) {
        CounterStateReducer.reduce(state.counterState, action)
        ErrorStateReducer.reduce(state.errorState, action)
        NavigationReducer.reduce(state.screenState, action)
    }
}
