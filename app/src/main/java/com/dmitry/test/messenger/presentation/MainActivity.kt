package com.dmitry.test.messenger.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dmitry.test.messenger.presentation.screen.EmailVerificationScreen
import com.dmitry.test.messenger.presentation.screen.SplashScreen
import com.dmitry.test.messenger.presentation.ui.theme.MessengerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessengerTheme {
                MessengerApp()
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object AuthGraph : Screen("auth_graph")
    data object MainGraph : Screen("main_graph")
    data object EmailVerification : Screen("email_verification_route")
}

@Composable
fun MessengerApp(

){
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            val authState by authViewModel.userState.collectAsState()
            SplashScreen(navController, authState)
        }

        authGraph(navController)

        composable(Screen.EmailVerification.route) {
            val authState by authViewModel.userState.collectAsState()

            authViewModel.sendEmailVerification()

            EmailVerificationScreen(navController, authState, authViewModel)
        }

        mainGraph(navController)
    }
}

