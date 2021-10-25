package com.nkuppan.giphybrowser.presentation.searchlist

import android.app.Application
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StickersSearchViewModel @Inject constructor(
    aApplication: Application,
    repository: GiphyRepository
) : GiphyBrowserListViewModel(aApplication, Type.STICKERS, repository)
