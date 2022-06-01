package com.example.lesson5.ui.feed.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.data.model.ResultResponse
import com.example.lesson5.domain.DeletePostUseCase
import com.example.lesson5.domain.GetPostUseCase
import com.example.lesson5.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(

    private val getPostUseCase: GetPostUseCase,
    private val deletePostUseCase: DeletePostUseCase

) : ViewModel() {

    val postLiveData = MutableLiveData<Post>()
    val postDeleteLiveData = MutableLiveData<Boolean>()
    lateinit var postId: String

    fun getPost(postId: String) {
        this.postId = postId
        viewModelScope.launch {
            postLiveData.value = getPostUseCase.invoke(postId)
        }
    }

    fun deletePost() = viewModelScope.launch {
        postDeleteLiveData.value = deletePostUseCase.invoke(postId)
    }
}