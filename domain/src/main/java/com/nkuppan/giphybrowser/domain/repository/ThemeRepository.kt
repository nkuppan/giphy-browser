package com.nkuppan.giphybrowser.domain.repository

import com.nkuppan.giphybrowser.domain.model.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    /**
     * Storing the selected theme
     */
    fun saveTheme(theme: Theme, coroutineScope: CoroutineScope)

    /**
     * Storing the selected theme
     */
    fun applyTheme(coroutineScope: CoroutineScope)


    /**
     * Reading the selected theme
     */
    fun getSelectedTheme(): Flow<Theme>


    /**
     * Reading the list of theme available in the data store
     */
    fun getThemes(): List<Theme>
}