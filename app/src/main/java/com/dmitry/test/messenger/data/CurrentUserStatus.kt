package com.dmitry.test.messenger.data

sealed class CurrentUserStatus {
    object Unauthenticated : CurrentUserStatus()
    data class Authenticated(val uid: String) : CurrentUserStatus()
}