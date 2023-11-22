package com.example.imagepager.di

import com.example.imagepager.data.remote.api.KtorApiClient
import com.example.imagepager.data.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiClient(): KtorApiClient {
        return KtorApiClient()
    }

    @Provides
    @Singleton
    fun provideImageRepository(apiClient: KtorApiClient): ImageRepository {
        return ImageRepository(apiClient)
    }
}