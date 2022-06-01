package com.example.lesson5.domain

import com.example.lesson5.data.repository.ImageRepository
import javax.inject.Inject

class DeleteImageUseCase @Inject constructor(

    private val imageRepository: ImageRepository

) {

    suspend operator fun invoke(imageId: String): Boolean {
      return  imageRepository.deleteImage(imageId)
    }

}