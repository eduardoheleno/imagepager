package com.example.imagepager.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val photographer_id: Int,
    val avg_color: String,
    val src: Src,
    val liked: Boolean,
    val alt: String
)
