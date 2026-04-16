package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): String {
        return repository.signUp(email, password)
    }
}