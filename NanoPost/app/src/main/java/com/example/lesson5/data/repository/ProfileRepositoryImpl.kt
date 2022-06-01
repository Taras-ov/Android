package com.example.lesson5.data.repository

import com.example.lesson5.data.NanoPostApiService
import com.example.lesson5.data.mapers.toProfile
import com.example.lesson5.model.Profile
import java.lang.IllegalStateException
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(

    private val apiService: NanoPostApiService,
    private val repository: PreferencesRepository

) : ProfileRepository {

    override suspend fun getProfile(profileId: String?): Profile {
        val result = repository.getUserId()
        return when {
            profileId != null -> {
                apiService.getUserProfile(profileId).toProfile()
            }
            result != null -> {
                apiService.getUserProfile(result).toProfile()
            }
            else -> {
                throw IllegalStateException("UserId cannot be null")
            }
        }
    }

}