package com.example.lesson5.data.repository

import com.example.lesson5.data.model.CheckUsernameResult
import com.example.lesson5.data.model.TokenResponse

interface AuthRepository {

    suspend fun checkUsername(username: String): CheckUsernameResult

    suspend fun login(username: String, password: String): TokenResponse

    suspend fun register(username: String, password: String): TokenResponse
}