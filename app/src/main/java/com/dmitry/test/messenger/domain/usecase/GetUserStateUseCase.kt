package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.data.CurrentUserStatus
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import com.dmitry.test.messenger.domain.repository.UserState
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetUserStateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository: UserProfileRepository
) {
    suspend operator fun invoke(): UserState {
        when (val authStatus = authRepository.getUserStatus()) {
            is CurrentUserStatus.Unauthenticated -> {
                return UserState.Unauthenticated
            }

            is CurrentUserStatus.Authenticated -> {
                return if (!authRepository.isEmailVerified()) {
                    UserState.EmailNotVerified
                } else{
                    if (profileRepository.getProfile(authStatus.uid) == null){
                        UserState.ProfileNotCreated
                    } else {
                        UserState.Authenticated(authStatus.uid)
                    }
                }
            }
        }
    }
}