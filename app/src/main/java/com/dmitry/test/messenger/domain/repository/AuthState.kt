package com.dmitry.test.messenger.domain.repository

sealed class AuthState {
    object Unauthenticated : AuthState()
    object EmailNotVerified : AuthState()
    object ProfileNotCreated : AuthState()
    data class Authenticated(val uid: String) : AuthState()
}