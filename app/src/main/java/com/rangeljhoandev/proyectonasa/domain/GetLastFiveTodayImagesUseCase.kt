package com.rangeljhoandev.proyectonasa.domain

import com.rangeljhoandev.proyectonasa.data.PostImageRepository
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel

class GetLastFiveTodayImagesUseCase {

    private val repository = PostImageRepository()

    suspend operator fun invoke(startDate: String, endDate: String): List<PostImageModel> =
        repository.getLastFiveTodayImages(startDate, endDate)

}