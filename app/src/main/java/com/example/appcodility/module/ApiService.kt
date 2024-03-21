package com.example.appcodility.module

import com.example.appcodility.model.DogImageData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("breeds/image/random")
    suspend fun fetchDogImage(): DogImageData
}