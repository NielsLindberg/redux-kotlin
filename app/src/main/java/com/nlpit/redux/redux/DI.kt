package com.nlpit.redux.redux

import com.nlpit.redux.redux.middleware.AsyncMiddleware
import com.nlpit.redux.redux.middleware.LoggerMiddleware

object DI {
    val store = DefaultStore(
        initialState = AppState(CounterState()),
        reducer = AppStateReducer,
        middleware = listOf(AsyncMiddleware(), LoggerMiddleware())
    )
}

