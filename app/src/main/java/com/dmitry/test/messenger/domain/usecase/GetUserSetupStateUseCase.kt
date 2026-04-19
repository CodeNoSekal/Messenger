package com.dmitry.test.messenger.domain.usecase

import android.util.Log
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserState
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetUserSetupStateUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): UserState {
        val user = repository.getCurrentUser() ?: return UserState.Unauthenticated

        return try {
            user.reload().await()

            if (!user.isEmailVerified) {
                UserState.EmailNotVerified
            } else {
                UserState.Authenticated(user.uid)
            }

        } catch (e: FirebaseAuthInvalidUserException) {
            UserState.Unauthenticated

        } catch (e: Exception) {
            if (!user.isEmailVerified) {
                UserState.EmailNotVerified
            } else {
                UserState.Authenticated(user.uid)
            }
        }
    }
}