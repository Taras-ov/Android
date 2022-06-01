package com.example.lesson5.data.repository

import android.content.SharedPreferences

interface PreferencesRepository {
    val sharedPreferences: SharedPreferences

    fun getUserId(): String?

    fun addToken(token: String, userId: String)

    fun getToken(): String?
}