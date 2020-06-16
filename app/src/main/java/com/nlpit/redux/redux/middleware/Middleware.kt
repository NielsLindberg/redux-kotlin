package com.nlpit.redux.redux.middleware

import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action

interface Middleware {
    fun invoke(state: AppState, action: Action, dispatch: Dispatch, next: Next): Action
}