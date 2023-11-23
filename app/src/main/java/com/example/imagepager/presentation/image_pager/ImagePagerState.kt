package com.example.imagepager.presentation.image_pager

import com.example.imagepager.data.remote.model.Photo

data class ImagePagerState(
    val isFetchingApi: Boolean = true,
    val error: String = "",
    val photos: List<Photo>? = null
)