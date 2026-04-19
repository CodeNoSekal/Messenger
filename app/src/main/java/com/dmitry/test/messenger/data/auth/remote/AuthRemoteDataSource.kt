package com.dmitry.test.messenger.data.auth.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth
) {
    fun signUp(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password)

    fun signIn(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password)

    fun getCurrentUser() = auth.currentUser
}