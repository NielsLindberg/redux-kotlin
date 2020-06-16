package com.nlpit.redux.redux

import Reducer
import androidx.compose.MutableState
import androidx.compose.mutableStateOf
import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.middleware.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

typealias Dispatch = (Action) -> Unit
typealias Next = (AppState, Action, Dispatch) -> Action

interface Store {
    fun dispatch(action: Action)
    val state: MutableState<AppState>
}

class DefaultStore(
    initialState: AppState,
    private val reducer: Reducer<AppState>,
    private val middleware: List<Middleware>
) : Store, CoroutineScope {

    override val state: MutableState<AppState> = mutableStateOf(initialState)

    override fun dispatch(action: Action) {
        launch {
            val newAction = applyMiddleware(state.value, action)
            state.value = reducer(state.value, newAction)
        }
    }

    private fun applyMiddleware(state: AppState, action: Action): Action {
        return next(0)(state, action, ::dispatch)
    }

    private fun next(index: Int): Next {
        if (index == middleware.size) {
            // Last link of the chain. It just returns the action as is.
            return { _, action, _ -> action }
        }
        return { state, action, dispatch  ->
            middleware[index].invoke(
                state,
                action,
                dispatch,
                next(index + 1)
            )
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}