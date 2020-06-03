package com.nlpit.redux.redux.actions

interface Action

sealed class CounterActions: Action {
    object Init: CounterActions()
    object Increment: CounterActions()
    object Decrement: CounterActions()
    data class GeneralError(val error: Exception): CounterActions()
}

sealed class WaitActions: Action {
    object StartWaiting: WaitActions()
    object Waiting: WaitActions()
    object TimedOut: WaitActions()
    class FinishedWaiting(val results: String): WaitActions()
}