package com.example.lesson5.domain

import com.example.lesson5.data.model.TokenResponse
import com.example.lesson5.data.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(

    private val authRepository: AuthRepository

) {

    suspend operator fun invoke(username: String, password: String): TokenResponse {
        return authRepository.register(username, password)
    }
}