package com.example.imagepager.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val total_results: Int,
    val page: Int,
    val per_page: Int,
    val photos: Array<Photo>,
    val next_page: String
)
