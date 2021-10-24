package com.nkuppan.giphybrowser.browser.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nkuppan.giphybrowser.browser.model.Type
import com.nkuppan.giphybrowser.browser.repository.GiphyRepository
import com.nkuppan.giphybrowser.core.domain.model.GiphyImage

class GiphyPagingSource(
    private val type: Type,
    private val repository: GiphyRepository
) : PagingSource<Int, GiphyImage>() {

    var query: String = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        val (success, response) = when (type) {
            Type.GIF -> repository.getGifResponse(
                query, page * pageSize, pageSize
            )
            Type.STICKERS -> repository.getStickersResponse(
                query, page * pageSize, pageSize
            )
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
        return state.anchorPosition
    }
}
