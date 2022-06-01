package com.example.lesson5.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.lesson5.data.NanoPostApiService
import com.example.lesson5.data.mapers.toImage
import com.example.lesson5.data.paging.StringImagedPagingSource
import com.example.lesson5.model.Image
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(

    private val apiService: NanoPostApiService

) : ImageRepository {
    override fun getProfileImages(profileId: String?, count: Int
    ): Flow<PagingData<Image>> {
        return Pager(PagingConfig(pageSize = count, enablePlaceholders = false), "0") {
            StringImagedPagingSource(profileId, apiService)
        }.flow.map {
            it.map { apiImage ->
                apiImage.toImage()
            }
        }
    }

    override suspend fun getImage(imageId: String): Image {
        return apiService.getImage(imageId).toImage()
    }

    override suspend fun deleteImage(imageId: String): Boolean {
        return apiService.deleteImage(imageId).result
    }

}