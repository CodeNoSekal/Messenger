package com.dmitry.test.messenger.data.repository

import com.dmitry.test.messenger.data.remote.ProfileRemoteDataStore
import com.dmitry.test.messenger.domain.UserProfile
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val remote: ProfileRemoteDataStore
) : UserProfileRepository{
    override suspend fun createProfile(uid: String, profile: UserProfile) {
        remote.createProfile(uid, profile)
    }

    override suspend fun observeProfile(uid: String): Flow<UserProfile?> {
        return remote.observeProfile(uid)
    }

    override suspend fun getProfile(uid: String): UserProfile? {
        return remote.getProfile(uid)
    }

}