package com.dmitry.test.messenger.domain.repository

interface AuthRepository {

    suspend fun signUp(email: String, password: String): String

    suspend fun signIn(email: String, password: String): String

    fun getCurrentUser(): String?
}