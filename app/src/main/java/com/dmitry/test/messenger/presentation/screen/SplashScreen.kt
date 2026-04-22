package com.dmitry.test.messenger.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dmitry.test.messenger.domain.repository.UserState
import com.dmitry.test.messenger.presentation.Screen

@Composable
fun SplashScreen(
    navController: NavHostController,
    userState: UserState
) {
    LaunchedEffect(userState) {
        when(userState) {
            is UserState.Authenticated -> {
                navController.navigate(Screen.MainGraph.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            is UserState.Unauthenticated -> {
                navController.navigate(Screen.AuthGraph.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            is UserState.EmailNotVerified -> {
                navController.navigate(Screen.EmailVerification.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            is UserState.ProfileNotCreated -> {
                navController.navigate(Screen.ProfileCreation.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }

            else -> {}
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}