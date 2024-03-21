package com.example.appcodility

import com.example.appcodility.model.DogImageData
import com.example.appcodility.module.ApiService
import com.example.appcodility.repository.DogRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryUnitTest {

    @Test
    fun fetchRandomDogImage() {
        // Create a mock instance of DogApiService
        val dogApiService = mockk<ApiService>()
        // Mock the behavior of fetchRandomDogImage() to return a DogImage
        val dogImage = DogImageData("https://example.com/dog.jpg", "success")
        coEvery { dogApiService.fetchDogImage() } returns dogImage
        // Create an instance of DogRepository with the mocked DogApiService
        val dogRepository = DogRepository(dogApiService)
        val result = runBlocking { dogRepository.fetchDogImage() }
        assertEquals("https://example.com/dog.jpg", result.message)
        assertEquals("success", result.status)
    }


    @Test
    fun fetchRandomDogImages() {
        val dogApiService = mockk<ApiService>()
        coEvery { dogApiService.fetchDogImage() } returns DogImageData("https://example.com/dog1.jpg","success")
        val dogRepository = DogRepository(dogApiService)
        val result = runBlocking { dogRepository.fetchDogImages(3) }
        assertEquals(3, result.size)
        assertEquals("https://example.com/dog1.jpg", result[0].message)
        assertEquals("https://example.com/dog1.jpg", result[1].message)
        assertEquals("https://example.com/dog1.jpg", result[2].message)
    }

}