package com.example.imagepager.presentation.image_pager

import com.example.imagepager.data.remote.model.Page

data class ImagePagerState(
    val isFetchingApi: Boolean = true,
    val page: Page? = null
)