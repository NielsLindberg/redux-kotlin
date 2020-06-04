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
class AsyncMiddleware : Middleware<AppState>, CoroutineScope {
    override fun invoke(
        state: AppState,
        action: Action,
        dispatch: Dispatch,
        next: Next<AppState>,
        reducer: Reducer<AppState>
    ): Action {
        when (action) {
            CounterActions.Increment -> {
                async { wait10seconds(state, action, dispatch) }
            }
        }
        return next(state, action, dispatch, reducer)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default
}

suspend fun wait10seconds(state: AppState, action: Action, dispatch: Dispatch) {
    dispatch(WaitActions.StartWaiting)
    delay(1000) // pretend we are doing something asynchronous here

    dispatch(WaitActions.FinishedWaiting(state.counterState.string))
}
