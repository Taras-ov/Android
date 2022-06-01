package com.example.lesson5.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.lesson5.domain.GetPostsUseCase
import com.example.lesson5.domain.GetUserProfileUseCase
import com.example.lesson5.model.Post
import com.example.lesson5.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

    private val getPostsUseCase: GetPostsUseCase,
    private val getProfileUseCase: GetUserProfileUseCase

) : ViewModel() {

    val profileLiveData = MutableLiveData<Profile>()
    val postLiveData = MutableLiveData<PagingData<Post>>()

    fun getProfile() = viewModelScope.launch {
        profileLiveData.value = getProfileUseCase.invoke(null)
    }

    fun getPosts() = viewModelScope.launch {
        getPostsUseCase.invoke(null, 30).collect {
            postLiveData.value = it
        }
    }
}