package com.dmitry.test.messenger.data.repository

import android.util.Log
import com.dmitry.test.messenger.data.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.AuthState
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
): AuthRepository {

    override suspend fun signUp(email: String, password: String): String {
        val result = remote.signUp(email, password).await()
        return result.user?.uid ?: throw Exception("No UID")
    }

    override suspend fun signIn(email: String, password: String): String {
        val result = remote.signIn(email, password).await()
        return result.user?.uid ?: throw Exception("No UID")
    }

    override suspend fun getCurrentUser(): AuthState {
        val user = remote.getCurrentUser()?: return AuthState.Unauthenticated

        return try {
            user.reload().await()

            if (!user.isEmailVerified){
                AuthState.EmailNotVerified
            } else {
                AuthState.Authenticated(user.uid)
            }
        }catch (e: Exception){
            AuthState.Unauthenticated
        }
    }
}