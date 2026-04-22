package com.dmitry.test.messenger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmitry.test.messenger.domain.UserProfile
import com.dmitry.test.messenger.domain.usecase.CreateUserProfileUseCase
import com.dmitry.test.messenger.domain.usecase.GetUserIdUseCase
import com.dmitry.test.messenger.domain.usecase.ObserveUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val createUserProfileUseCase: CreateUserProfileUseCase,
    private val observeUserProfileUseCase: ObserveUserProfileUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
): ViewModel() {

    fun createProfile(profile: UserProfile) {
        val uid = getUserIdUseCase()

        viewModelScope.launch {
            uid?.let {
                createUserProfileUseCase(it, profile)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val profileState: StateFlow<UserProfile?> =
        flow {
            val uid = getUserIdUseCase()
            emit(uid)
        }.flatMapLatest { uid ->
            if(uid == null) flowOf(null)
            else observeUserProfileUseCase(uid)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}