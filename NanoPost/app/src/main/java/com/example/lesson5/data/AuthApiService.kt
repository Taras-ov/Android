package com.example.lesson5.data

import com.example.lesson5.data.model.CheckUsernameResponse
import com.example.lesson5.data.model.RegisterRequest
import com.example.lesson5.data.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @GET("checkUsername")
    suspend fun checkUsername(
        @Query("username") username: String
    ): CheckUsernameResponse

    @GET("login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): TokenResponse

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): TokenResponse
}