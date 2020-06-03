package com.nlpit.redux.redux

object DI {
    val store = DefaultStore(
        initialState = AppState(CounterState()),
        reducer = AppStateReducer,
        middleware = listOf(::loggerMiddleware)
    )
}

