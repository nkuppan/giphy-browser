package com.nkuppan.giphybrowser

import com.google.common.truth.Truth.assertThat
import com.nkuppan.giphybrowser.base.BaseCoroutineAndMockTest
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.GiphyImageAttributes
import com.nkuppan.giphybrowser.domain.model.Resource
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GiphyRepositoryMockitoTest : BaseCoroutineAndMockTest() {

    @Mock
    private lateinit var giphyRepository: GiphyRepository

    @Test
    fun searchGiphyGifQueryWithEmptyResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Success(listOf())
        )

        val gifResponseResult = giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Success::class.java)
        val successResponse = gifResponseResult as Resource.Success
        assertThat(successResponse).isNotNull()
        assertThat(successResponse.data).isEmpty()
    }

    @Test
    fun searchGiphyGifQueryWithResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Success(listOf(fakeGifImageObject))
        )

        val gifResponseResult = giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Success::class.java)

        val successResponse = gifResponseResult as Resource.Success
        assertThat(successResponse).isNotNull()
        assertThat(successResponse.data).isNotEmpty()
        assertThat(successResponse.data[0]).isEqualTo(fakeGifImageObject)

        //Validating object
        assertThat(successResponse.data[0].id).isEqualTo(FAKE_ID)
        assertThat(successResponse.data[0].title).isEqualTo(FAKE_SEARCH_QUERY)
        assertThat(successResponse.data[0].type).isEqualTo(GIF)
    }

    @Test
    fun searchGiphyGifQueryWithErrorResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Error(KotlinNullPointerException())
        )

        val gifResponseResult = giphyRepository.getGifResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Error::class.java)
        val error = gifResponseResult as Resource.Error
        assertThat(error).isNotNull()
        assertThat(error.exception).isNotNull()
    }

    @Test
    fun searchGiphyStickerQueryWithEmptyResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Success(listOf())
        )

        val gifResponseResult = giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Success::class.java)
        val successResponse = gifResponseResult as Resource.Success
        assertThat(successResponse).isNotNull()
        assertThat(successResponse.data).isEmpty()
    }

    @Test
    fun searchGiphyStickerQueryWithResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Success(listOf(fakeStickerImageObject))
        )

        val gifResponseResult = giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Success::class.java)

        val successResponse = gifResponseResult as Resource.Success
        assertThat(successResponse).isNotNull()
        assertThat(successResponse.data).isNotEmpty()
        assertThat(successResponse.data[0]).isEqualTo(fakeStickerImageObject)

        //Validating object
        assertThat(successResponse.data[0].id).isEqualTo(FAKE_ID)
        assertThat(successResponse.data[0].title).isEqualTo(FAKE_SEARCH_QUERY)
        assertThat(successResponse.data[0].type).isEqualTo(STICKER)
    }

    @Test
    fun searchGiphyStickerQueryWithErrorResult() = runBlockingTest(testCoroutineDispatcher) {
        whenever(giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)).thenReturn(
            Resource.Error(KotlinNullPointerException())
        )

        val gifResponseResult = giphyRepository.getStickersResponse(FAKE_SEARCH_QUERY, 1, 20)
        assertThat(gifResponseResult).isInstanceOf(Resource.Error::class.java)
        val error = gifResponseResult as Resource.Error
        assertThat(error).isNotNull()
        assertThat(error.exception).isNotNull()
    }

    companion object {

        const val FAKE_ID = "1"
        const val FAKE_SEARCH_QUERY = "Android"
        const val GIF = "gif"
        const val STICKER = "sticker"

        private val fakeImageAttribute = GiphyImageAttributes("100", "100", "")

        val fakeGifImageObject = GiphyImage(
            FAKE_ID,
            FAKE_SEARCH_QUERY,
            GIF,
            "",
            fakeImageAttribute,
            fakeImageAttribute
        )

        val fakeStickerImageObject = GiphyImage(
            FAKE_ID,
            FAKE_SEARCH_QUERY,
            STICKER,
            "",
            fakeImageAttribute,
            fakeImageAttribute
        )
    }
}