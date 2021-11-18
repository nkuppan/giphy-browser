package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase

class GiphyStickersPagingSource(
    private val query: String,
    private val stickerSearchUseCase: StickerSearchUseCase
) : PagingSource<Int, GiphyImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        val (success, response) = stickerSearchUseCase.invoke(
            query, page * pageSize, pageSize
        )
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
