package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import com.nkuppan.giphybrowser.domain.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class GiphyBrowserListViewModel(
    private val type: Type,
    private val gifSearchUseCase: GifSearchUseCase? = null,
    private val stickerSearchUseCase: StickerSearchUseCase? = null
) : BaseViewModel() {

    private var queryString: String = ""

    var searchResult: Flow<PagingData<GiphyImage>> = flow {}
        private set

    fun refreshSearchWithQuery(aQuery: String): Boolean {

        val trimmedQuery = aQuery.trim()

        if (trimmedQuery == queryString) {
            return false
        }

        queryString = trimmedQuery

        searchResult = Pager(PagingConfig(PAGE_SIZE)) {
            if (type == Type.GIF) {
                GiphyGifPagingSource(
                    queryString, gifSearchUseCase!!
                )
            } else {
                GiphyStickersPagingSource(
                    queryString, stickerSearchUseCase!!
                )
            }
        }.flow.cachedIn(viewModelScope)

        return true
    }
}
