package com.example.lesson5.domain

import androidx.paging.PagingData
import com.example.lesson5.data.repository.ImageRepository
import com.example.lesson5.model.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProfileImagesUseCase @Inject constructor(

    private val imageRepository: ImageRepository

) {

    operator fun invoke(
        profileId: String?,
        count: Int,
    ): Flow<PagingData<Image>> {
        return imageRepository.getProfileImages(profileId, count)
    }
}