package com.example.lesson5.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(

    override val sharedPreferences: SharedPreferences

) : PreferencesRepository {

    companion object {
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
    }

    override fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    override fun addToken(token: String, userId: String) {
        sharedPreferences.edit {
            this.putString(KEY_TOKEN, token)
            this.putString(KEY_USER_ID, userId)
        }
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

}