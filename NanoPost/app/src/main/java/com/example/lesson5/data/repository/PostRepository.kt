package com.example.lesson5.data.repository

import androidx.paging.PagingData
import com.example.lesson5.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPosts(profileId: String?, count: Int): Flow<PagingData<Post>>

    suspend fun getPost(postId: String): Post

    suspend fun createPost(text: String?, images: List<ByteArray>?): Post

    suspend fun deletePost(postId: String): Boolean
}