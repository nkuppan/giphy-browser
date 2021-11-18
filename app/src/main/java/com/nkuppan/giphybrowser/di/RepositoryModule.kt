package com.nkuppan.giphybrowser.di

import android.content.Context
import com.nkuppan.giphybrowser.di.AppModule.dataStore
import com.nkuppan.giphybrowser.domain.datastore.ThemeDataStore
import com.nkuppan.giphybrowser.domain.network.GiphyApiService
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.domain.repository.GiphyRepositoryImpl
import com.nkuppan.giphybrowser.domain.repository.ThemeRepository
import com.nkuppan.giphybrowser.domain.repository.ThemeRepositoryImpl
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideThemeDataStore(@ApplicationContext context: Context): ThemeDataStore {
        return ThemeDataStore(context.dataStore)
    }

    @Provides
    @Singleton
    fun provideThemeRepository(
        dataStore: ThemeDataStore
    ): ThemeRepository {
        return ThemeRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideGiphyRepository(
        service: GiphyApiService
    ): GiphyRepository {
        return GiphyRepositoryImpl(
            service
        )
    }

    @Provides
    @Singleton
    fun provideGiphyGifSearchUseCase(
        giphyRepository: GiphyRepository
    ): GifSearchUseCase {
        return GifSearchUseCase(giphyRepository)
    }

    @Provides
    @Singleton
    fun provideGiphyStickerSearchUseCase(
        giphyRepository: GiphyRepository
    ): StickerSearchUseCase {
        return StickerSearchUseCase(giphyRepository)
    }
}
