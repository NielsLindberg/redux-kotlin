import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.Action
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.redux.actions.NavigateActions

typealias Reducer <S> = (S, Action) -> S

val CounterStateReducer: Reducer<CounterState> = { old, action ->
    val newState = when (action) {
        is CounterActions.Increment -> old.copy(counter = old.counter + 1)
        is CounterActions.Decrement -> old.copy(counter = old.counter - 1)
        else -> old
    }
    if (newState.counter.rem(2) == 0 && action is CounterActions) {
        newState.copy(nestedCounterState = newState.nestedCounterState.copy(counter = newState.nestedCounterState.counter + 1))
    } else {
        newState
    }
}

val ErrorStateReducer: Reducer<ErrorState> = { old, action ->
    when (action) {
        is CounterActions.GeneralError -> ErrorState(message = action.error.message)
        else -> old
    }
}

val NavigationReducer: Reducer<ScreenState> = { old, action ->
    when (action) {
        is NavigateActions.HomeScreen -> old.copy(currentScreen = Screen.Home)
        is NavigateActions.YoScreen -> old.copy(currentScreen = Screen.Yo)
        is NavigateActions.LolScreen -> old.copy(currentScreen = Screen.Lol)
        else -> old
    }
}

val AppStateReducer: Reducer<AppState> = { old, action ->
    AppState(
        counterState = CounterStateReducer(old.counterState, action),
        errorState = ErrorStateReducer(old.errorState, action),
        screenState = NavigationReducer(old.screenState, action)
    )
}