package com.example.lesson5.ui.profile.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.lesson5.domain.DeleteImageUseCase
import com.example.lesson5.domain.GetImageUseCase
import com.example.lesson5.domain.GetProfileImagesUseCase
import com.example.lesson5.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(

    private val getProfileImagesUseCase: GetProfileImagesUseCase,

): ViewModel() {

    val profileImagesLiveData = MutableLiveData<PagingData<Image>>()

    fun getProfileImages(profileId: String) = viewModelScope.launch {
        getProfileImagesUseCase.invoke(profileId, 30).collect {
            profileImagesLiveData.value = it
        }
    }
}