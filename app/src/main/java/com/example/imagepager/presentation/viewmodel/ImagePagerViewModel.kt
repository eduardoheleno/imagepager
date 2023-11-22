package com.example.imagepager.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagepager.data.repository.ImageRepository
import com.example.imagepager.presentation.image_pager.ImagePagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePagerViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _state = mutableStateOf(ImagePagerState())
    val state: State<ImagePagerState> = _state

    init {
        fetchImages()
    }

    private fun fetchImages() = viewModelScope.launch(Dispatchers.IO) {
        val fetchedImages = imageRepository.fetchImages()
        _state.value = ImagePagerState(
            isFetchingApi = false,
            page = fetchedImages
        )
    }
}