package com.example.lesson5.data.repository

import androidx.paging.*
import com.example.lesson5.data.NanoPostApiService
import com.example.lesson5.data.mapers.toPost
import com.example.lesson5.data.paging.StringKeyedPagingSource
import com.example.lesson5.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(

    private val apiService: NanoPostApiService

) : PostRepository {

    override fun getPosts(
        profileId: String?,
        count: Int,
    ): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = count, enablePlaceholders = false), "0") {
            StringKeyedPagingSource(profileId, apiService)
        }.flow.map {
            it.map { apiPost ->
                apiPost.toPost()
            }
        }
    }

    override suspend fun getPost(postId: String): Post {
        return apiService.getPost(postId).toPost()
    }

    override suspend fun createPost(text: String?, images: List<ByteArray>?): Post {

        val listOfImages = mutableListOf<MultipartBody.Part?>()

        for (i in 0..3) {
            val nameNumber = i + 1
            val image = images?.getOrNull(index = i)?.let {
                MultipartBody.Part.createFormData(
                    "image$nameNumber", "image.jpg", RequestBody.create(
                        MediaType.parse("image/jpg"), it
                    )
                )
            }
            listOfImages.add(image)
        }

        val image1 = listOfImages[0]
        val image2 = listOfImages[1]
        val image3 = listOfImages[2]
        val image4 = listOfImages[3]

        return apiService.createPost(
            text?.let { RequestBody.create(MediaType.parse("text/plain"), it) },
            image1,
            image2,
            image3,
            image4
        ).toPost()
    }

    override suspend fun deletePost(postId: String): Boolean {
        return apiService.deletePost(postId).result
    }

}