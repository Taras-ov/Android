package com.example.lesson5.ui.feed.posts

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostCreateViewModel @Inject constructor(

) : ViewModel() {

    val postCreateLiveData = MutableLiveData<List<Uri>>()

    fun onImageAdded(uri: Uri) {
        val list = postCreateLiveData.value.orEmpty()
        postCreateLiveData.value = list.toMutableList().apply {
            add(uri)
        }
    }

}