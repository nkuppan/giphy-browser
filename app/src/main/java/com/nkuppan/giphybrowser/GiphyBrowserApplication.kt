package com.nkuppan.giphybrowser

import com.nkuppan.giphybrowser.core.BaseApplication
import com.nkuppan.giphybrowser.domain.repository.ThemeRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class GiphyBrowserApplication : BaseApplication() {

    @Inject
    lateinit var themeRepository: ThemeRepository

    override fun onCreate() {
        super.onCreate()
        setupTheme()
    }

    private fun setupTheme() {
        GlobalScope.launch {
            themeRepository.applyTheme(this)
        }
    }
}
