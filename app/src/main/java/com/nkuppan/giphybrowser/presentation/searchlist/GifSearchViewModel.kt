package com.nkuppan.giphybrowser.presentation.searchlist

import android.app.Application
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifSearchViewModel @Inject constructor(
    application: Application,
    gifSearchUseCase: GifSearchUseCase
) : GiphyBrowserListViewModel(
    application,
    Type.GIF,
    gifSearchUseCase = gifSearchUseCase
)
