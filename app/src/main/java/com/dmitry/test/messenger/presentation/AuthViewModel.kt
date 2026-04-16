package com.dmitry.test.messenger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.dmitry.test.messenger.domain.usecase.GetCurrentUserUseCase
import com.dmitry.test.messenger.domain.usecase.SignInUseCase
import com.dmitry.test.messenger.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val uid = signUpUseCase(email, password)
            } catch (e: Exception) {

            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val uid = signInUseCase(email, password)
            } catch (e: Exception) {

            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return getCurrentUserUseCase() != null
    }
}