package com.example.imagepager.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagepager.data.repository.ImageRepository
import com.example.imagepager.presentation.image_pager.ImagePagerEvent
import com.example.imagepager.presentation.image_pager.ImagePagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ImagePagerViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {
    private val _state = mutableStateOf(ImagePagerState())
    val state: State<ImagePagerState> = _state

    fun onEvent(event: ImagePagerEvent) {
        when (event) {
            is ImagePagerEvent.FetchPage -> fetchPage(event.page)
            is ImagePagerEvent.Error -> {
                _state.value = ImagePagerState(
                    isFetchingApi = false,
                    photos = null,
                    error = event.error
                )
            }
        }
    }

    private fun fetchPage(page: Int = 1) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val fetchedPage = imageRepository.fetchImages(page)
            var photosListToState = fetchedPage.photos

            if (!_state.value.photos.isNullOrEmpty()) {
                val newPhotosList = _state.value.photos!!.toMutableList()
                newPhotosList.addAll(fetchedPage.photos)
                photosListToState = newPhotosList.toTypedArray()
            }

            _state.value = ImagePagerState(
                isFetchingApi = false,
                photos = photosListToState.asList()
            )
        } catch (e: Exception) {
            onEvent(ImagePagerEvent.Error(e.toString()))
        }
    }
}