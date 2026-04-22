package com.dmitry.test.messenger.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dmitry.test.messenger.presentation.screen.MainScreen

sealed class MainScreen(val route: String) {
    data object Home : MainScreen("home")
}

fun NavGraphBuilder.mainGraph() {
    navigation(
        startDestination = MainScreen.Home.route,
        route = Screen.MainGraph.route
    ) {
        composable(MainScreen.Home.route) {
            MainScreen()
        }
    }
}
