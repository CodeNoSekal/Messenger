package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.UserProfile
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(uid: String): Flow<UserProfile?> {
        return repository.observeProfile(uid)
    }
}