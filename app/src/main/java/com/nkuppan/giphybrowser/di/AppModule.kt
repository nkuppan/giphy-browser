package com.nkuppan.giphybrowser.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val DATA_STORE_NAME = "giphy_browser_data_store"

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)
}