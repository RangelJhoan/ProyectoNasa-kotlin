package com.rangeljhoandev.proyectonasa.data

import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import com.rangeljhoandev.proyectonasa.data.model.PostImageProvider
import com.rangeljhoandev.proyectonasa.data.network.PostImageService

class PostImageRepository {

    private val api = PostImageService()

    suspend fun getPostImage(): PostImageModel {
        val response = api.getTodayImage()
        PostImageProvider.postImage = response
        return response
    }

    suspend fun getLastFiveTodayImages(
        startDate: String,
        endDate: String
    ): List<PostImageModel> {
        val response = api.getLastFiveTodayImages(startDate, endDate)
        PostImageProvider.lastFiveTodayImages = response
        return response
    }

}