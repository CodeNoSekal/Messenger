package com.dmitry.test.messenger.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.test.messenger.domain.usecase.GetCurrentUserUseCase
import com.dmitry.test.messenger.domain.usecase.SignInUseCase
import com.dmitry.test.messenger.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    sealed class AuthUiState {
        object Idle: AuthUiState()
        object Loading : AuthUiState()
        data class Success(val uid: String): AuthUiState()
        data class Error(val message: String?): AuthUiState()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val uid = signUpUseCase(email, password)
                _uiState.value = AuthUiState.Success(uid)
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val uid = signInUseCase(email, password)
                _uiState.value = AuthUiState.Success(uid)
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun isUserLoggedIn(): Boolean {
        return getCurrentUserUseCase() != null
    }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}