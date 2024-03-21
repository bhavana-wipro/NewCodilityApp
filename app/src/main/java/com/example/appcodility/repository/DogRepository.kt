package com.example.appcodility.repository

import com.example.appcodility.model.DogImageData
import com.example.appcodility.module.ApiService
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val apiService: ApiService
    ){
    suspend fun fetchDogImage(): DogImageData {
        return apiService.fetchDogImage()
    }
    suspend fun fetchDogImages(count: Int): List<DogImageData> {
        val dogImages = mutableListOf<DogImageData>()
        repeat(count) {
        val image = apiService.fetchDogImage()
        dogImages.add(image)
    }
        return dogImages
    }
}