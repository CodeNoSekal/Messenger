package com.dmitry.test.messenger.presentation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

sealed class MainScreen(val route: String) {
    data object Home : MainScreen("home")
}

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(
        startDestination = MainScreen.Home.route,
        route = Screen.MainGraph.route
    ) {
        composable(MainScreen.Home.route) {
            Text("Главный экран приложения 🎉")
        }
    }
}
