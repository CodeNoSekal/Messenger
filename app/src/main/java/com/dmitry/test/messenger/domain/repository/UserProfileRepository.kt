package com.dmitry.test.messenger.domain.repository

import com.dmitry.test.messenger.domain.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun createProfile(uid: String, profile: UserProfile)
    suspend fun observeProfile(uid: String): Flow<UserProfile?>
    suspend fun getProfile(uid: String): UserProfile?
}