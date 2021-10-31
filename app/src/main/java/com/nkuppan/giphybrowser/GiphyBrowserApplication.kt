package com.nkuppan.giphybrowser

import com.nkuppan.giphybrowser.core.BaseApplication
import com.nkuppan.giphybrowser.domain.repository.ThemeRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GiphyBrowserApplication : BaseApplication() {

    @Inject
    lateinit var themeRepository: ThemeRepository

    @DelicateCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        setupTheme()
    }

    @DelicateCoroutinesApi
    private fun setupTheme() {
        GlobalScope.launch {
            themeRepository.applyTheme(this, Dispatchers.Main)
        }
    }
}
