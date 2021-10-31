package com.nkuppan.giphybrowser.presentation.theme

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nkuppan.giphybrowser.core.extension.Event
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import com.nkuppan.giphybrowser.domain.model.Theme
import com.nkuppan.giphybrowser.domain.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ThemeViewModel @Inject constructor(
    application: Application,
    private val themeRepository: ThemeRepository
) : BaseViewModel(application) {

    private val _openThemeDialog = MutableLiveData<Event<Unit>>()
    val openThemeDialog: LiveData<Event<Unit>> = _openThemeDialog

    val theme = themeRepository.getSelectedTheme()

    fun getThemes() = themeRepository.getThemes()

    fun setTheme(theme: Theme) = themeRepository.saveTheme(theme, viewModelScope)

    fun chooseTheme() {
        _openThemeDialog.value = Event(Unit)
    }
}
