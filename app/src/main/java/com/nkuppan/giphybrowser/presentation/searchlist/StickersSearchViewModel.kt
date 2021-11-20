package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StickersSearchViewModel @Inject constructor(
    private val stickerSearchUseCase: StickerSearchUseCase
) : GiphySearchViewModel<GiphyImage>() {

    override fun getPagingSource(): PagingSource<Int, GiphyImage> {
        return GiphyStickersPagingSource(
            queryString,
            stickerSearchUseCase
        )
    }
}
