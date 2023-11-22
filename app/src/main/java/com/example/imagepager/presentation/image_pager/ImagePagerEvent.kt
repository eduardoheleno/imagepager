package com.example.imagepager.presentation.image_pager

sealed class ImagePagerEvent {
    object FetchImages : ImagePagerEvent()
    data class Error(val error: String) : ImagePagerEvent()
}