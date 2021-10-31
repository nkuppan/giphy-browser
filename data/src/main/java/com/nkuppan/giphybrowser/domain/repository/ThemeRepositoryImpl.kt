package com.nkuppan.giphybrowser.domain.repository

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.nkuppan.giphybrowser.domain.R
import com.nkuppan.giphybrowser.domain.datastore.ThemeDataStore
import com.nkuppan.giphybrowser.domain.model.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ThemeRepositoryImpl(private val dataStore: ThemeDataStore) : ThemeRepository {

    private fun getDefaultTheme(): Theme {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> Theme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, R.string.system_default)
            else -> Theme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, R.string.set_by_battery_saver)
        }
    }

    override fun saveTheme(theme: Theme, coroutineScope: CoroutineScope) {
        val mode = theme.mode
        AppCompatDelegate.setDefaultNightMode(mode)
        coroutineScope.launch {
            dataStore.setMode(mode)
        }
    }

    override fun applyTheme(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            val theme = getSelectedTheme().first()
            withContext(Dispatchers.Main) {
                val mode = theme.mode
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
    }

    override fun getSelectedTheme(): Flow<Theme> {
        val defaultTheme = getDefaultTheme()
        val defaultMode = defaultTheme.mode
        val themes = getThemes()
        return dataStore.getMode(defaultMode).map { mode ->
            themes.find { theme -> theme.mode == mode } ?: defaultTheme
        }
    }

    override fun getThemes(): List<Theme> {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> listOf(
                Theme(AppCompatDelegate.MODE_NIGHT_NO, R.string.light),
                Theme(AppCompatDelegate.MODE_NIGHT_YES, R.string.dark),
                Theme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, R.string.set_by_battery_saver),
                Theme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, R.string.system_default)
            )
            else -> listOf(
                Theme(AppCompatDelegate.MODE_NIGHT_NO, R.string.light),
                Theme(AppCompatDelegate.MODE_NIGHT_YES, R.string.dark),
                Theme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, R.string.system_default)
            )
        }
    }
}
