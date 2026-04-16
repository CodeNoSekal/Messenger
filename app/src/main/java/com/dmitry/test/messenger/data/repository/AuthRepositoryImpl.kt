package com.dmitry.test.messenger.data.repository

import com.dmitry.test.messenger.data.remote.AuthRemoteDataSource
import com.dmitry.test.messenger.domain.repository.AuthRepository
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

    override fun getCurrentUser(): String? {
        return remote.getCurrentUser()?.uid
    }
}