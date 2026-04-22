package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.repository.AuthRepository
import com.dmitry.test.messenger.domain.repository.UserState
import javax.inject.Inject

class GetUserEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getUserEmail()
    }
}