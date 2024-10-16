package com.rangeljhoandev.proyectonasa.domain

import com.rangeljhoandev.proyectonasa.data.PostImageRepository
import com.rangeljhoandev.proyectonasa.data.model.PostImageModel

class GetPostImageUseCase {

    private val repository = PostImageRepository()

    suspend operator fun invoke(): PostImageModel = repository.getPostImage()

}