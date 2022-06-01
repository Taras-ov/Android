package com.example.lesson5.domain

import com.example.lesson5.data.model.CheckUsernameResult
import com.example.lesson5.data.repository.AuthRepository
import javax.inject.Inject

class CheckUsernameUseCase @Inject constructor(

    private val authRepository: AuthRepository

) {

    suspend operator fun invoke(
        username: String
    ): CheckUsernameResult {
        return authRepository.checkUsername(username)
    }
}