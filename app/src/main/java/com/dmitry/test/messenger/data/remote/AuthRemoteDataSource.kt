package com.dmitry.test.messenger.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun signUp(email: String, password: String): FirebaseUser? {
        return auth
            .createUserWithEmailAndPassword(email, password)
            .await()
            .user
    }

    suspend fun signIn(email: String, password: String): FirebaseUser? {
        return auth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}