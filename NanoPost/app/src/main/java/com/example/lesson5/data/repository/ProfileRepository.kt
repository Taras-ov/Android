package com.example.lesson5.data.repository

import com.example.lesson5.model.Profile

interface ProfileRepository {

    suspend fun getProfile(profileId: String?): Profile

}