package com.example.imagepager.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Src(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)