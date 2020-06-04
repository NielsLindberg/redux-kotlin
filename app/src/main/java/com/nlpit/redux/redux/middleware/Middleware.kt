package com.nlpit.redux.redux.middleware

import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action

interface Middleware<State> {
    fun invoke(state: State, action: Action, dispatch: Dispatch, next: Next<State>, reducer: Reducer<State>): Action
}