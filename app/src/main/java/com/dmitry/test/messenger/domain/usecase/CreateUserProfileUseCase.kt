package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.UserProfile
import com.dmitry.test.messenger.domain.repository.UserProfileRepository
import javax.inject.Inject

class CreateUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(uid: String, profile: UserProfile) {
        repository.createProfile(uid, profile)
    }
}