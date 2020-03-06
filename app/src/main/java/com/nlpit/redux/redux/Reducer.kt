package com.nlpit.redux.redux

typealias Reducer <S> = (S, Action) -> S

val CounterStateReducer: Reducer<CounterState> = { old, action ->
    when (action) {
        is CounterActions.Decrement -> CounterState()
        is CounterActions.Increment -> old.copy(value = old.value + 1)
        is CounterActions.Decrement -> old.copy(value = old.value - 1)
        else -> old
    }
}