package com.rangeljhoandev.proyectonasa.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import com.rangeljhoandev.proyectonasa.domain.GetLastFiveTodayImagesUseCase
import com.rangeljhoandev.proyectonasa.domain.GetPostImageUseCase
import kotlinx.coroutines.launch

class PostImageViewModel : ViewModel() {

    val postImage = MutableLiveData<PostImageModel>()
    var getPostImageUseCase = GetPostImageUseCase()

    val lastFiveTodayImages = MutableLiveData<List<PostImageModel>>()
    var getLastFiveTodayImagesUseCase = GetLastFiveTodayImagesUseCase()

    fun onCreate(startDate: String, endDate: String) {
        viewModelScope.launch {
            postImage.postValue(getPostImageUseCase())
            lastFiveTodayImages.postValue(getLastFiveTodayImagesUseCase(startDate, endDate))
        }
    }

}