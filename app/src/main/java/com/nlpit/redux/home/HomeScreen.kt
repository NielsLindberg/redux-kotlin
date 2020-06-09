package com.nlpit.redux.home

import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.*
import com.nlpit.redux.AppDrawer
import com.nlpit.redux.redux.AppState
import com.nlpit.redux.redux.Screen

@Composable
fun HomeScreen(
    state: AppState,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Home,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "Jetnews") }
            )
        },
        bodyContent = { modifier ->
            HomeScreenContent(state, modifier)
        }
    )
}

@Composable
private fun HomeScreenContent(
    state: AppState,
    modifier: Modifier = Modifier
) {
    VerticalScroller {
        Column(modifier) {
        }
    }
}

