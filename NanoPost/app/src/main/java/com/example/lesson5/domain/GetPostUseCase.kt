package com.example.lesson5.domain

import com.example.lesson5.data.repository.PostRepository
import com.example.lesson5.model.Post
import javax.inject.Inject

class GetPostUseCase @Inject constructor(

    private val postRepository: PostRepository

) {

    suspend operator fun invoke(
        postId: String
    ): Post {
        return postRepository.getPost(postId)
    }

}