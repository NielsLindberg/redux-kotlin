package com.nlpit.redux.redux.middleware

import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.redux.actions.WaitActions
import kotlinx.coroutines.*

import kotlin.coroutines.CoroutineContext

/**
 * Actions to run through flow methods
 */
class AsyncMiddleware : Middleware, CoroutineScope {
    override fun invoke(
        state: AppState,
        action: Action,
        dispatch: Dispatch,
        next: Next
    ): Action {
        when (action) {
            CounterActions.Increment -> {
                async { wait10seconds(state, action, dispatch) }
            }
        }
        return next(state, action, dispatch)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default
}

suspend fun wait10seconds(state: AppState, action: Action, dispatch: Dispatch) {
    dispatch(WaitActions.StartWaiting)
    delay(5000) // pretend we are doing something asynchronous here

    dispatch(WaitActions.FinishedWaiting(state.counterState.string))
}
