package com.dmitry.test.messenger.presentation

import android.os.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.test.messenger.domain.repository.UserState
import com.dmitry.test.messenger.domain.usecase.SignInUseCase
import com.dmitry.test.messenger.domain.usecase.SignUpUseCase
import com.dmitry.test.messenger.domain.usecase.GetUserSetupStateUseCase
import com.dmitry.test.messenger.domain.usecase.SendEmailVerificationUseCase
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
    private val getUserSetupStateUseCase: GetUserSetupStateUseCase,
    private val sendEmailVerificationUseCase: SendEmailVerificationUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState.asStateFlow()

    private val _authUiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    init {
        updateUserState()
    }

    sealed class AuthUiState{
        object Idle: AuthUiState()
        object Loading: AuthUiState()
        data class Success(val uid: String): AuthUiState()
        data class Error(val message: String?): AuthUiState()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            try {
                val uid = signUpUseCase(email, password)
                _authUiState.value = AuthUiState.Success(uid)
                updateUserState()
            } catch (e: Exception) {
                _authUiState.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authUiState.value = AuthUiState.Loading
            try {
                val uid = signInUseCase(email, password)
                _authUiState.value = AuthUiState.Success(uid)
                updateUserState()
            } catch (e: Exception) {
                _authUiState.value = AuthUiState.Error(e.message)
            }
        }
    }

    fun updateUserState() {
        viewModelScope.launch {
            _userState.value = getUserSetupStateUseCase()
        }
    }

    fun sendEmailVerification() {
        viewModelScope.launch {
            sendEmailVerificationUseCase()
        }
    }

    fun resetAuthUiState(){
        _authUiState.value = AuthUiState.Idle
    }

}