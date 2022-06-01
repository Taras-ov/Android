package com.example.lesson5.ui.profile.images

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.domain.DeleteImageUseCase
import com.example.lesson5.domain.GetImageUseCase
import com.example.lesson5.model.Image
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(

    private val deleteImageUseCase: DeleteImageUseCase,
    private val getImageUseCase: GetImageUseCase

    ):ViewModel() {

    val imageDeleteLiveData = MutableLiveData<Boolean>()
    val imageLiveData = MutableLiveData<Image>()
    lateinit var imageId: String

    fun deleteImage() = viewModelScope.launch {
        imageDeleteLiveData.value = deleteImageUseCase.invoke(imageId)
    }

    fun getImage(imageId: String) {
        this.imageId = imageId
        viewModelScope.launch {
            imageLiveData.value = getImageUseCase.invoke(imageId)
        }
    }

}