package com.nlpit.redux.redux

import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.actions.CounterActions

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

val AppStateReducer: Reducer<AppState> = { old, action ->
    AppState(
        counterState = CounterStateReducer(old.counterState, action),
        errorState = ErrorStateReducer(old.errorState, action)
    )
}