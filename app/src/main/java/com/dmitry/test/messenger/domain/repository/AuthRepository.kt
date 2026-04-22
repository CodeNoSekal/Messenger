package com.dmitry.test.messenger.domain.repository

import com.dmitry.test.messenger.data.CurrentUserStatus
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signUp(email: String, password: String): String

    suspend fun signIn(email: String, password: String): String

    fun getUserId(): String?
    suspend fun isEmailVerified(): Boolean

    suspend fun sendEmailVerification()
    suspend fun getUserStatus(): CurrentUserStatus
    suspend fun getUserEmail(): String?
}