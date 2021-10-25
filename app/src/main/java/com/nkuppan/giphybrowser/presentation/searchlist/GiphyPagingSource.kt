package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase

class GiphyPagingSource(
    private val type: Type,
    private val query: String,
    private val aGifSearchUseCase: GifSearchUseCase? = null,
    private val aStickerSearchUseCase: StickerSearchUseCase? = null
) : PagingSource<Int, GiphyImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        val (success, response) = when (type) {
            Type.GIF -> aGifSearchUseCase?.invoke(
                query, page * pageSize, pageSize
            ) ?: (false to emptyList())
            Type.STICKERS -> aStickerSearchUseCase?.invoke(
                query, page * pageSize, pageSize
            ) ?: (false to emptyList())
        }
        return if (success) {
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page.dec(),
                nextKey = if (response.isEmpty()) null else page.inc()
            )
        } else {
            LoadResult.Error(RuntimeException("Failed to fetch response with page = $page and page size = $pageSize"))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GiphyImage>): Int? {
        return null
    }
}
