package com.dmitry.test.messenger.presentation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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

