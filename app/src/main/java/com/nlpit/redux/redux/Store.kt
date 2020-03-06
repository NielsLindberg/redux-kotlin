package com.nlpit.redux.redux

import androidx.databinding.BaseObservable
import timber.log.Timber

typealias StoreSubscriber <S> = (S) -> Unit

interface Dispatcher {
    fun dispatch(action: Action)
}
interface Store<S : State>: Dispatcher {
    fun add(subsriber: StoreSubscriber<S>): Boolean
    fun remove(subscriber: StoreSubscriber<S>): Boolean
}

class DefaultStore<S : State>(
    initialState: S,
    private val reducer: Reducer<S>
) : Store<S>, BaseObservable() {

    private var state: S = initialState
    set(value) {
        Timber.d("Redux > Store > oldState: $state)")
        field = value
        Timber.d("Redux > Store > newState: $value)")
        subscribers.forEach { it(value) }
    }
    private val subscribers = mutableSetOf<StoreSubscriber<S>>()

    override fun dispatch(action: Action) {
        Timber.d("Redux > Store > Dispatch($action)")
        state = reducer(state, action)
    }

    override fun add(subsriber: StoreSubscriber<S>) = subscribers.add(subsriber)


    override fun remove(subscriber: StoreSubscriber<S>) = subscribers.remove(subscriber)

}