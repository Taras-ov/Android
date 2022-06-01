package com.example.lesson5.domain

import androidx.paging.PagingData
import com.example.lesson5.data.repository.PostRepository
import com.example.lesson5.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(

    private val postRepository: PostRepository

) {

    operator fun invoke(
        profileId: String?,
        count: Int,
    ): Flow<PagingData<Post>> {
        return postRepository.getPosts(profileId, count)
    }
}