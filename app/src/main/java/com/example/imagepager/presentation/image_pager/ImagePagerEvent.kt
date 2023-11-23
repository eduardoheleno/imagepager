package com.example.imagepager.presentation.image_pager

sealed class ImagePagerEvent {
    data class FetchPage(val page: Int = 1) : ImagePagerEvent()
    data class Error(val error: String) : ImagePagerEvent()
}