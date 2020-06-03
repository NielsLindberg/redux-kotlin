package com.nlpit.redux.redux.middleware

import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action
import timber.log.Timber

/**
 * Logs oldstate, action, newstate for all actions. (state is not actually being reduced but newState is merely how the
 * new state would look if it was reduced with the action. (not possible to inject logging after the actual reduce since middleware
 * by definition is between dispatch and reduce)
 */
class LoggerMiddleware<S: State>: Middleware<S> {
    override fun invoke(
        state: S,
        action: Action,
        dispatch: Dispatch,
        next: Next<S>,
        reducer: Reducer<S>
    ): Action {
        Timber.d("Redux > Store > oldState $state")
        Timber.d("Redux > Store > Action: $action")
        val newAction = next(state, action, dispatch, reducer)
        val newState = reducer(state, action)
        Timber.d("Redux > Store > newState: $newState)")
        return newAction
    }
}

