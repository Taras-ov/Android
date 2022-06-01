package com.example.lesson5.data.repository

import com.example.lesson5.data.AuthApiService
import com.example.lesson5.data.model.CheckUsernameResult
import com.example.lesson5.data.model.RegisterRequest
import com.example.lesson5.data.model.TokenResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(

    private val apiService: AuthApiService

) : AuthRepository {
    override suspend fun checkUsername(username: String): CheckUsernameResult {
        return apiService.checkUsername(username).result
    }

    override suspend fun login(username: String, password: String): TokenResponse {
        return apiService.login(username, password)
    }

    override suspend fun register(username: String, password: String): TokenResponse {
        val registerRequest = RegisterRequest(username, password)
        return apiService.register(registerRequest)
    }
}