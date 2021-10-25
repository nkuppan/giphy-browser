package com.nkuppan.giphybrowser.di

import com.nkuppan.giphybrowser.domain.network.GiphyApiService
import com.nkuppan.giphybrowser.domain.network.model.GiphyImageDtoMapper
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.domain.repository.GiphyRepositoryImpl
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GiphyApiService.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): GiphyApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideGiphyDtoMapper(): GiphyImageDtoMapper {
        return GiphyImageDtoMapper()
    }

    @Provides
    @Singleton
    fun provideGiphyRepository(
        service: GiphyApiService,
        mapper: GiphyImageDtoMapper
    ): GiphyRepository {
        return GiphyRepositoryImpl(
            service,
            mapper
        )
    }

    @Provides
    @Singleton
    fun provideGiphyGifSearchUseCase(
        aGiphyRepository: GiphyRepository
    ): GifSearchUseCase {
        return GifSearchUseCase(aGiphyRepository)
    }

    @Provides
    @Singleton
    fun provideGiphyStickerSearchUseCase(
        aGiphyRepository: GiphyRepository
    ): StickerSearchUseCase {
        return StickerSearchUseCase(aGiphyRepository)
    }
}
