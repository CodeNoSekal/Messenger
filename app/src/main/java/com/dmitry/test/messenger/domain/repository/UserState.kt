package com.dmitry.test.messenger.domain.repository

sealed class UserState {
    object Loading : UserState()
    object Unauthenticated : UserState()
    object EmailNotVerified : UserState()
    object ProfileNotCreated : UserState()
    data class Authenticated(val uid: String) : UserState()
}