package com.rangeljhoandev.proyectonasa.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/planetary/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}