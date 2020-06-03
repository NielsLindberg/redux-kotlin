package com.nlpit.redux.redux

typealias StoreSubscriber <S> = (S) -> Unit
typealias Dispatch = (Action) -> Unit
typealias Next<State> = (State, Action, Dispatch, Reducer<State>) -> Action
typealias Middleware<State> = (State, Action, Dispatch, Next<State>, Reducer<State>) -> Action

interface Store<S : State> {
    fun subscribe(subscriber: StoreSubscriber<S>)
    fun remove(subscriber: StoreSubscriber<S>)
}

class DefaultStore<S : State>(
    initialState: S,
    private val reducer: Reducer<S>,
    private val middleware: List<Middleware<S>>
) : Store<S> {

    private var state: S = initialState
        set(value) {
            field = value
            subscribers.forEach { it(value) }
        }
    private val subscribers = mutableListOf<StoreSubscriber<S>>()

    fun dispatch(action: Action) {
        val newAction = applyMiddleware(state, action, reducer)
        val newState = reducer(state, newAction)

        if (newState == state) {
            return
        }
        state = newState
    }

    private fun applyMiddleware(state: S, action: Action, reducer: Reducer<S>): Action {
        return next(0)(state, action, ::dispatch, reducer)
    }

    private fun next(index: Int): Next<S> {
        if (index == middleware.size) {
            // Last link of the chain. It just returns the action as is.
            return { _, action, _ , _-> action }
        }
        return { state, action, dispatch, reducer ->
            middleware[index].invoke(
                state,
                action,
                dispatch,
                next(index + 1),
                reducer
            )
        }
    }

    override fun subscribe(subscriber: StoreSubscriber<S>) {
        subscribers.add(subscriber)
    }

    override fun remove(subscriber: StoreSubscriber<S>) {
        subscribers.remove(subscriber)
    }
}