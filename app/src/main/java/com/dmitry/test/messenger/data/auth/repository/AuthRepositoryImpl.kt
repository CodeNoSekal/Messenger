package com.dmitry.test.messenger.data.auth.repository

import com.dmitry.test.messenger.data.auth.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
): AuthRepository {

    override suspend fun signUp(email: String, password: String): FirebaseUser {
        val result = remote.signUp(email, password).await()
        return result.user ?: throw Exception("No UID")
    }

    override suspend fun signIn(email: String, password: String): FirebaseUser {
        val result = remote.signIn(email, password).await()
        return result.user ?: throw Exception("No UID")
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return remote.getCurrentUser()
    }
}