package com.nlpit.redux.redux

import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import com.nlpit.redux.redux.middleware.AsyncMiddleware
import com.nlpit.redux.redux.middleware.LoggerMiddleware

object DI {
    val store = DefaultStore(
        initialState = AppState(
            counterState = mutableStateOf(CounterState()),
            errorState = mutableStateOf(ErrorState("init")),
            screenState = mutableStateOf(ScreenState())
        ),
        reducer = AppStateReducer,
        middleware = listOf(AsyncMiddleware(), LoggerMiddleware())
    )
    val data = TestData()
}

class TestData(var counter: MutableState<Int> = mutableStateOf(0))
