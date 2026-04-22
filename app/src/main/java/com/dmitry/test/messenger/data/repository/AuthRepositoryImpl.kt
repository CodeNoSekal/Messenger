package com.dmitry.test.messenger.data.repository

import com.dmitry.test.messenger.data.CurrentUserStatus
import com.dmitry.test.messenger.data.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserState
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
): AuthRepository {

    override suspend fun signUp(email: String, password: String): String {
        val user = remote.signUp(email, password)
        return user?.uid ?: throw Exception("No UID")
    }

    override suspend fun signIn(email: String, password: String): String {
        val user = remote.signIn(email, password)
        return user?.uid ?: throw Exception("No UID")
    }

    override fun getUserId(): String? {
        return remote.getCurrentUser()?.uid
    }

    override suspend fun sendEmailVerification() {
        val user = remote.getCurrentUser() ?: return
        user.sendEmailVerification().await()
    }

    override suspend fun getUserStatus(): CurrentUserStatus {
        val user = remote.getCurrentUser() ?: return CurrentUserStatus.Unauthenticated

        return try {
            user.reload().await()
            CurrentUserStatus.Authenticated(user.uid)

        } catch (e: FirebaseAuthInvalidUserException) {
            CurrentUserStatus.Unauthenticated

        } catch (e: Exception) {
            UserState.Authenticated(user.uid)
        } as CurrentUserStatus
    }

    override suspend fun isEmailVerified(): Boolean {
        val user = remote.getCurrentUser() ?: return false

        return user.isEmailVerified
    }

    override suspend fun getUserEmail(): String? {
        return remote.getCurrentUser()?.email
    }
}