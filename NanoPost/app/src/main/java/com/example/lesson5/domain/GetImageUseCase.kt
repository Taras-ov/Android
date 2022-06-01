package com.example.lesson5.domain

import com.example.lesson5.data.repository.ImageRepository
import com.example.lesson5.model.Image
import javax.inject.Inject

class GetImageUseCase @Inject constructor(

    private val imageRepository: ImageRepository

) {

    suspend operator fun invoke(imageId: String): Image {
        return imageRepository.getImage(imageId)
    }
}