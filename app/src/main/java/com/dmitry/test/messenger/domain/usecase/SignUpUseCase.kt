package com.dmitry.test.messenger.domain.usecase

import com.dmitry.test.messenger.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): String {
        val user = repository.signUp(email, password)
        return user.uid
    }
}