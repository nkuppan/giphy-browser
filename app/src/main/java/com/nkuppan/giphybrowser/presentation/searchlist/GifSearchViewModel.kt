package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GifSearchViewModel @Inject constructor(
    private val gifSearchUseCase: GifSearchUseCase
) : GiphySearchViewModel<GiphyImage>() {

    override fun getPagingSource(): PagingSource<Int, GiphyImage> {
        return GiphyGifPagingSource(
            queryString,
            gifSearchUseCase
        )
    }
}
