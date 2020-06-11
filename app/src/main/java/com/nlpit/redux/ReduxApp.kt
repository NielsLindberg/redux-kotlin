package com.nlpit.redux

import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.Home
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.nlpit.redux.home.HomeScreen
import com.nlpit.redux.redux.AppState
import com.nlpit.redux.redux.DI
import com.nlpit.redux.redux.Screen
import com.nlpit.redux.redux.actions.CounterActions
import com.nlpit.redux.redux.actions.NavigateActions
import com.nlpit.redux.theme.reduxTheme

@Composable
fun ReduxApp(state: AppState) {
    reduxTheme {
        AppContent(state)
    }
}

@Composable
private fun AppContent(state: AppState) {
    Column {
        Button(onClick = { DI.store.dispatch(CounterActions.Decrement) }) {
            Text("Decrement")
        }
        Divider()
        Text(state.counterState.string)
        Divider()
        Button(onClick = { DI.store.dispatch(CounterActions.Increment) }) {
            Text("Increment")
        }
    }
}