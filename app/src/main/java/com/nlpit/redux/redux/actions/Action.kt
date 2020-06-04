package com.nlpit.redux.redux.actions


open class Action(val payload: Any? = null) {

    override fun toString(): String {
        payload?.let {
            return this.javaClass.superclass?.simpleName + "." + this.javaClass.simpleName + " {$it}"
        } ?: return this.javaClass.superclass?.simpleName + "." + this.javaClass.simpleName

    }
}

sealed class CounterActions: Action() {
    object Init: CounterActions()
    object Increment: CounterActions()
    object Decrement: CounterActions()
    data class GeneralError(val error: Exception): CounterActions()
}

sealed class WaitActions(payload: Any? = null): Action(payload) {
    object StartWaiting: WaitActions()
    object Waiting: WaitActions()
    object TimedOut: WaitActions()
    class FinishedWaiting(val results: String): WaitActions(results)
}