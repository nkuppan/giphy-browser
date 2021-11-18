package com.nkuppan.giphybrowser.presentation.searchlist

import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StickersSearchViewModel @Inject constructor(
    stickerSearchUseCase: StickerSearchUseCase
) : GiphyBrowserListViewModel(
    Type.STICKERS,
    stickerSearchUseCase = stickerSearchUseCase
)
