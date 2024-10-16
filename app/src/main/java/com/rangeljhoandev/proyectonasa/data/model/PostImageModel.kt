package com.rangeljhoandev.proyectonasa.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostImageModel(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val title: String
) : Parcelable