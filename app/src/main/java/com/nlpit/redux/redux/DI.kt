package com.nlpit.redux.redux

import AppStateReducer
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import com.nlpit.redux.redux.middleware.AsyncMiddleware
import com.nlpit.redux.redux.middleware.LoggerMiddleware

object DI {
    val store = DefaultStore(
        initialState = AppState(
            counterState = CounterState(),
            errorState = ErrorState("init"),
            screenState = ScreenState()
        ),
        reducer = AppStateReducer,
        middleware = listOf(AsyncMiddleware(), LoggerMiddleware())
    )
}