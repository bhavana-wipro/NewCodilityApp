package com.example.appcodility.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcodility.model.DogImageData
import com.example.appcodility.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val urlRepository: DogRepository
) : ViewModel() {
    private val _dogImage = MutableLiveData<DogImageData>()
    val dogImage: LiveData<DogImageData> = _dogImage

    private val _dogImages = MutableLiveData<List<DogImageData>>()
    val dogImages: LiveData<List<DogImageData>> = _dogImages

    fun fetchRandomDogImage() {
        viewModelScope.launch {
                val dogImage = urlRepository.fetchDogImage()
            _dogImage.value = dogImage
        }
    }

    fun fetchRandomDogImages(count: Int) {
        viewModelScope.launch {
            val dogImages = urlRepository.fetchDogImages(count)
            _dogImages.value = dogImages
        }
    }
}