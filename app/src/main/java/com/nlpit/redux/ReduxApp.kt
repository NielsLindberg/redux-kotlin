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
fun ReduxApp(state: AppState) {
    reduxTheme {
        AppContent(state)
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
        textComposable(state = state.counterState)
        Divider()
        Button(onClick = { DI.store.dispatch(CounterActions.Increment) }) {
            Text("Increment")
        }
    }
}

@Composable
fun textComposable(state: MutableState<CounterState>) {
    Timber.d("compose: number")
    Text(state.value.string)
}

@Composable
fun stationFeatureTabs(state: MutableState<ScreenState>) {
    Timber.d("compose: tab")
    TabRow(items = state.value.screens, selectedIndex = state.value.currentScreen.index) { int, screen ->
        Tab(
            onSelected = { DI.store.dispatch(screen.action) },
            selected = state.value.currentScreen.index == int
        ) {
            Text(screen.title)
        }
    }
}