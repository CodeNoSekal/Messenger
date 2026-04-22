package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): String? {
        return repository.getUserId()
    }
}