package com.nlpit.redux.redux

interface Action

sealed class CounterActions: Action {
    object Init: CounterActions()
    object Increment: CounterActions()
    object Decrement: CounterActions()
}