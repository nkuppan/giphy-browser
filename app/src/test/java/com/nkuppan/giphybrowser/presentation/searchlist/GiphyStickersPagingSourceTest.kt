package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.paging.PagingSource
import com.google.common.truth.Truth
import com.nkuppan.giphybrowser.base.BaseCoroutineAndMockTest
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Resource
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.domain.usecase.StickerSearchUseCase
import com.nkuppan.giphybrowser.utils.MockConstants
import com.nkuppan.giphybrowser.utils.MockConstants.Companion.FAKE_SEARCH_QUERY
import com.nkuppan.giphybrowser.utils.MockConstants.Companion.PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GiphyStickersPagingSourceTest : BaseCoroutineAndMockTest() {

    private val giphyStickerMockData = arrayListOf<GiphyImage>().apply {
        add(MockConstants.getGiphyStickerObject("1"))
        add(MockConstants.getGiphyStickerObject("2"))
    }

    @Mock
    private lateinit var giphyRepository: GiphyRepository

    private lateinit var giphyStickersPagingSource: GiphyStickersPagingSource

    private lateinit var stickerSearchUseCase: StickerSearchUseCase

    override fun setUp() {
        super.setUp()
        stickerSearchUseCase = StickerSearchUseCase(giphyRepository)

        giphyStickersPagingSource = GiphyStickersPagingSource(FAKE_SEARCH_QUERY, stickerSearchUseCase)
    }

    @Test
    fun `Check paging success result`() = runBlockingTest(testCoroutineDispatcher) {

        whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 0, PAGE_SIZE)).thenReturn(
            Resource.Success(giphyStickerMockData)
        )

        val load = giphyStickersPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = PAGE_SIZE,
                placeholdersEnabled = false
            )
        )

        Truth.assertThat(load).isEqualTo(
            PagingSource.LoadResult.Page(
                data = giphyStickerMockData,
                prevKey = null,
                nextKey = 1
            )
        )
    }

    @Test
    fun `Check paging success result with multiple pages`() =
        runBlockingTest(testCoroutineDispatcher) {

            whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 0, PAGE_SIZE)).thenReturn(
                Resource.Success(giphyStickerMockData)
            )

            //First page validation
            val firstPageResult = giphyStickersPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

            Truth.assertThat(firstPageResult).isEqualTo(
                PagingSource.LoadResult.Page(
                    data = giphyStickerMockData,
                    prevKey = null,
                    nextKey = 1
                )
            )

            whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1 * PAGE_SIZE, PAGE_SIZE)).thenReturn(
                Resource.Success(giphyStickerMockData)
            )

            //Second page validation
            val secondPageResult = giphyStickersPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

            Truth.assertThat(secondPageResult).isEqualTo(
                PagingSource.LoadResult.Page(
                    data = giphyStickerMockData,
                    prevKey = 0,
                    nextKey = 2
                )
            )
        }
}