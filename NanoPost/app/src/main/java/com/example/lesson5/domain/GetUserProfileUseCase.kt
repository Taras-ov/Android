package com.example.lesson5.domain

import com.example.lesson5.data.repository.ProfileRepository
import com.example.lesson5.model.Profile
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(

    private val profileRepository: ProfileRepository

) {

    suspend operator fun invoke(profileId: String?): Profile {
        return profileRepository.getProfile(profileId)
    }
}