package com.nkuppan.giphybrowser.browser.ui.viewmodel

import android.app.Application
import com.nkuppan.giphybrowser.browser.model.Type
import com.nkuppan.giphybrowser.browser.repository.GiphyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifSearchViewModel @Inject constructor(
    aApplication: Application,
    repository: GiphyRepository
) : GiphyBrowserListViewModel(aApplication, Type.GIF, repository)
