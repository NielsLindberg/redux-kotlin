package com.nlpit.redux.redux.middleware

import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action
import timber.log.Timber

/**
 * Logs oldstate, action, newstate for all actions. (state is not actually being reduced but newState is merely how the
 * new state would look if it was reduced with the action. (not possible to inject logging after the actual reduce since middleware
 * by definition is between dispatch and reduce)
 */
class LoggerMiddleware : Middleware {
    override fun invoke(
        state: AppState,
        action: Action,
        dispatch: Dispatch,
        next: Next
    ): Action {
        val newAction = next(state, action, dispatch)
        Timber.d("$action")

        return newAction
    }
}

