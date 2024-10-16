package com.rangeljhoandev.proyectonasa.data.network

import com.rangeljhoandev.proyectonasa.util.constants.NasaAPIConstants.Companion.API_KEY
import com.rangeljhoandev.proyectonasa.core.RetrofitHelper
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostImageService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getTodayImage(): PostImageModel {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                    retrofit.create(PostImageApiClient::class.java).getTodayImage(API_KEY)
                response.body() ?: PostImageModel("", "", "", "")
            } catch (e: Exception) {
                PostImageModel("", "", "", "")
            }
        }
    }

    suspend fun getLastFiveTodayImages(
        startDate: String,
        endDate: String
    ): List<PostImageModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response =
                    retrofit.create(PostImageApiClient::class.java).getTodayImagesXDateRange(
                        API_KEY, startDate, endDate
                    )
                response.body() ?: arrayListOf()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}