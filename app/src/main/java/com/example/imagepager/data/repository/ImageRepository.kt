package com.example.imagepager.data.repository

import com.example.imagepager.data.remote.api.KtorApiClient
import com.example.imagepager.data.remote.model.Page

class ImageRepository(
    private val apiClient: KtorApiClient
) {
    suspend fun fetchImages(page: Int): Page {
        return apiClient.fetchImages(DEFAULT_PER_PAGE, page)
    }

    companion object {
        const val DEFAULT_PER_PAGE = 10
    }
}