package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import com.nkuppan.giphybrowser.base.BaseCoroutineAndMockTest
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Resource
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.domain.usecase.GifSearchUseCase
import com.nkuppan.giphybrowser.utils.FAKE_SEARCH_QUERY
import com.nkuppan.giphybrowser.utils.PAGE_SIZE
import com.nkuppan.giphybrowser.utils.getGiphyGifObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GiphyGifPagingSourceTest : BaseCoroutineAndMockTest() {

    private val giphyGifMockData = arrayListOf<GiphyImage>().apply {
        add(getGiphyGifObject("1"))
        add(getGiphyGifObject("2"))
    }

    @Mock
    private lateinit var giphyRepository: GiphyRepository

    private lateinit var giphyGifPagingSource: GiphyGifPagingSource

    private lateinit var gifSearchUseCase: GifSearchUseCase

    override fun setUp() {
        super.setUp()
        gifSearchUseCase = GifSearchUseCase(giphyRepository)
        giphyGifPagingSource = GiphyGifPagingSource(FAKE_SEARCH_QUERY, gifSearchUseCase)
    }

    @Test
    fun checkPagingSuccessResult() = runTest {

        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 0, 2)).thenReturn(
            Resource.Success(giphyGifMockData)
        )

        val load = giphyGifPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = PAGE_SIZE,
                placeholdersEnabled = false
            )
        )

        assertThat(load).isEqualTo(
            PagingSource.LoadResult.Page(
                data = giphyGifMockData,
                prevKey = null,
                nextKey = 1
            )
        )
    }

    @Test
    fun checkPagingSuccessResultWithMultiplePages() = runTest {

        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 0, PAGE_SIZE)).thenReturn(
            Resource.Success(giphyGifMockData)
        )

        //First page validation
        val firstPageResult = giphyGifPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertThat(firstPageResult).isEqualTo(
            PagingSource.LoadResult.Page(
                data = giphyGifMockData,
                prevKey = null,
                nextKey = 1
            )
        )

        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 2, PAGE_SIZE)).thenReturn(
            Resource.Success(giphyGifMockData)
        )

        //Second page validation
        val secondPageResult = giphyGifPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertThat(secondPageResult).isEqualTo(
            PagingSource.LoadResult.Page(
                data = giphyGifMockData,
                prevKey = 0,
                nextKey = 2
            )
        )
    }
}