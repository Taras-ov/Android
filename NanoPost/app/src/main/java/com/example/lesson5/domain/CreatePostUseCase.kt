package com.example.lesson5.domain

import com.example.lesson5.data.repository.PostRepository
import com.example.lesson5.model.Image
import com.example.lesson5.model.Post
import javax.inject.Inject

class CreatePostUseCase @Inject constructor(

    private val postRepository: PostRepository

) {

    suspend operator fun invoke(text: String?, images: List<ByteArray>?): Post {
        return postRepository.createPost(text, images)
    }
}