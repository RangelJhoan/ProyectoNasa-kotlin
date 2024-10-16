package com.rangeljhoandev.proyectonasa.data.network

import com.rangeljhoandev.proyectonasa.data.model.PostImageModel
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface PostImageApiClient {

    @GET("apod")
    suspend fun getTodayImage(@Query("api_key") apiKey: String): Response<PostImageModel>

    @GET("apod")
    suspend fun getTodayImagesXDateRange(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): Response<ArrayList<PostImageModel>>

}