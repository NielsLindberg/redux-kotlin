package com.nlpit.redux.redux

import timber.log.Timber

fun loggerMiddleware(state: AppState, action: Action, dispatch: Dispatch, next: Next<AppState>, reducer: Reducer<AppState>): Action {
    Timber.d("Redux > Store > oldState $state")
    Timber.d("Redux > Store > Action: $action")
    val newAction = next(state, action, dispatch, reducer)
    val newState = reducer(state, action)
    Timber.d("Redux > Store > newState: $newState)")
    return newAction
}

