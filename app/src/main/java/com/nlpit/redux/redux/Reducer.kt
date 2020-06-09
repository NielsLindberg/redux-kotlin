package com.nlpit.redux.redux

import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.redux.actions.NavigateActions

typealias Reducer <S> = (S, Action) -> S

val CounterStateReducer: Reducer<CounterState> = { old, action ->
    when (action) {
        is CounterActions.Increment -> old.copy(value = old.value + 1)
        is CounterActions.Decrement -> old.copy(value = old.value - 1)
        else -> old
    }
}

val ErrorStateReducer: Reducer<ErrorState?> = { old, action ->
    when (action) {
        is CounterActions.GeneralError -> ErrorState(message = action.error.message)
        else -> null
    }
}

val NavigationReducer: Reducer<ScreenState> = { old, action ->
    when(action) {
        is NavigateActions.HomeScreen -> old.copy(currentScreen = Screen.Home)
        else -> old
    }
}

val AppStateReducer: Reducer<AppState> = { old, action ->
    AppState(
        counterState = CounterStateReducer(old.counterState, action),
        errorState = ErrorStateReducer(old.errorState, action),
        screenState = NavigationReducer(old.screenState, action)
    )
}