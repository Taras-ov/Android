package com.example.lesson5.domain

import com.example.lesson5.data.repository.PreferencesRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(

    private val preferencesRepository: PreferencesRepository

) {

    operator fun invoke(token: String, userId: String) {
        preferencesRepository.addToken(token, userId)
    }

}