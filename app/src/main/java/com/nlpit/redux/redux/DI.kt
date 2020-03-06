package com.nlpit.redux.redux

object DI {
    val store = DefaultStore(initialState = CounterState(), reducer = CounterStateReducer)
}