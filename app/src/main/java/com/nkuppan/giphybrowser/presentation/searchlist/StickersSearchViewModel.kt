package com.nkuppan.giphybrowser.presentation.searchlist

import android.app.Application
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StickersSearchViewModel @Inject constructor(
    aApplication: Application,
    aStickerSearchUseCase: StickerSearchUseCase
) : GiphyBrowserListViewModel(
    aApplication,
    Type.STICKERS,
    aStickerSearchUseCase = aStickerSearchUseCase
)
