package com.example.imagepager.data.remote.api

import com.example.imagepager.common.Constants
import com.example.imagepager.data.remote.model.Page
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorApiClient {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json { this.ignoreUnknownKeys = true }
            )
        }
        defaultRequest {
            header("Authorization", Constants.API_SECRET)
            host = "api.pexels.com"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }

    suspend fun fetchImages(perPage: Int, page: Int): Page {
        return httpClient.get("v1/curated?per_page=$perPage&page=$page").body()
    }
}