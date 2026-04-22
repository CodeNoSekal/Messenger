package com.dmitry.test.messenger.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dmitry.test.messenger.presentation.screen.SignInScreen
import com.dmitry.test.messenger.presentation.screen.SignUpScreen

sealed class AuthScreen(val route: String) {
    data object SignIn : AuthScreen("sign_in")
    data object SignUp : AuthScreen("sign_up")
}

fun NavGraphBuilder.authGraph(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    navigation(
        startDestination = AuthScreen.SignIn.route,
        route = Screen.AuthGraph.route
    ) {
        composable(AuthScreen.SignIn.route) {
            SignInScreen(navController, authViewModel)
        }

        composable(AuthScreen.SignUp.route) {
            SignUpScreen(navController, authViewModel)
        }
    }
}

