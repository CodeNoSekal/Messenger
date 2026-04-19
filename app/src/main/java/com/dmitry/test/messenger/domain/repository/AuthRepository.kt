package com.dmitry.test.messenger.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signUp(email: String, password: String): FirebaseUser

    suspend fun signIn(email: String, password: String): FirebaseUser

    suspend fun getCurrentUser(): FirebaseUser?
}