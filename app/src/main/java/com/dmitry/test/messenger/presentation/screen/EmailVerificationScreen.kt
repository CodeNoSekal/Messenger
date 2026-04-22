package com.dmitry.test.messenger.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dmitry.test.messenger.domain.repository.UserState
import com.dmitry.test.messenger.presentation.AuthViewModel
import com.dmitry.test.messenger.presentation.Screen

@Composable
fun EmailVerificationScreen(
    navController: NavController,
    userState: UserState,
    authViewModel: AuthViewModel
) {

    LaunchedEffect(userState) {
        if (userState is UserState.ProfileNotCreated) {
            navController.navigate(Screen.ProfileCreation.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(Unit) {
        authViewModel.sendEmailVerification()
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { authViewModel.updateUserState() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Email confirmed")
        }
    }
}