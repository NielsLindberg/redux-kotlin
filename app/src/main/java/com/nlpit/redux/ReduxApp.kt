package com.nlpit.redux

import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.*
import com.nlpit.redux.redux.*
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.theme.reduxTheme
import timber.log.Timber

@Composable
fun ReduxApp(state: MutableState<AppState>) {
    reduxTheme {
        AppContent(state.value)
    }
}

@Composable
private fun AppContent(state: AppState) {
    Column {
        stationFeatureTabs(state.screenState)
        Button(onClick = { DI.store.dispatch(CounterActions.Decrement) }) {
            Text("Decrement")
        }
        Divider()
        counterStateRow(state.counterState)
        Divider()
        Button(onClick = { DI.store.dispatch(CounterActions.Increment) }) {
            Text("Increment")
        }
    }
}

@Composable
fun counterStateRow(state: CounterState) {
    Timber.d("compose: counterstate row")
    Row {
        counterComposable(state = state)
        nestedCounterComposable(state = state.nestedCounterState)
    }
}
@Composable
fun counterComposable(state: CounterState) {
    Timber.d("compose: counter")
    Text(state.string)
}

@Composable
fun nestedCounterComposable(state: NestedCounterState) {
    Timber.d("compose: nested counter")
    Text(state.string)
}

@Composable
fun stationFeatureTabs(state: ScreenState) {
    Timber.d("compose: tab")
    TabRow(items = state.screens, selectedIndex = state.currentScreen.index) { int, screen ->
        Tab(
            onSelected = { DI.store.dispatch(screen.action) },
            selected = state.currentScreen.index == int
        ) {
            Text(screen.title)
        }
    }
}