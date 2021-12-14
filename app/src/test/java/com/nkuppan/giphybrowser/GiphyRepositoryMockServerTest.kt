package com.nkuppan.giphybrowser

import com.google.common.truth.Truth.assertThat
import com.nkuppan.giphybrowser.base.BaseCoroutineAndMockTest
import com.nkuppan.giphybrowser.data.network.GiphyApiService
import com.nkuppan.giphybrowser.data.repository.GiphyRepositoryImpl
import com.nkuppan.giphybrowser.domain.model.Resource
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.utils.MockResponseFileReader
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GiphyRepositoryMockServerTest : BaseCoroutineAndMockTest() {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var giphyRepository: GiphyRepository

    @Before
    override fun setUp() {
        super.setUp()

        mockWebServer = MockWebServer()
        mockWebServer.start()

        val giphyApiService: GiphyApiService = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()

        giphyRepository = GiphyRepositoryImpl(
            giphyApiService,
            testCoroutineDispatcher
        )
    }

    private fun setupMockResponse(responseFileName: String) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(responseFileName).content)
        mockWebServer.enqueue(response)
    }

    @Test
    fun readGiphySuccessJsonResponseFile() {
        val reader = MockResponseFileReader(GIF_SUCCESS_RESPONSE_FILE_NAME)
        assertThat(reader.content).isNotNull()
    }

    @Test
    fun readGiphyGifSuccessJsonResponseFile() = runBlocking {
        // Assign
        setupMockResponse(GIF_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val response = giphyRepository.getGifResponse(
            query = SEARCH_QUERY,
            PAGE,
            PAGE_SIZE
        )
        // Assert
        assertThat(response).isInstanceOf(Resource.Success::class.java)
        assertThat((response as Resource.Success).data).isNotEmpty()
    }

    @Test
    fun readGiphyGifSuccessJsonResponseFileWithResult() = runBlocking {
        // Assign
        setupMockResponse(GIF_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val response = giphyRepository.getGifResponse(
            query = SEARCH_QUERY,
            PAGE,
            PAGE_SIZE
        )

        // Assert
        assertThat(response).isInstanceOf(Resource.Success::class.java)
        val data = (response as Resource.Success).data
        assertThat(data).isNotEmpty()
        assertThat(data[0].type).isEqualTo(GIF)
    }

    @Test
    fun fetchGiphyStickersAndCheckResponse() = runBlocking {
        // Assign
        setupMockResponse(STICKERS_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val response = giphyRepository.getStickersResponse(
            query = SEARCH_QUERY,
            PAGE,
            PAGE_SIZE
        )

        //Assert
        assertThat(response).isInstanceOf(Resource.Success::class.java)
        val data = (response as Resource.Success).data
        assertThat(data).isNotEmpty()
    }

    @Test
    fun fetchGiphyStickersAndCheckResponseAndResult() = runBlocking {
        // Assign
        setupMockResponse(STICKERS_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val response = giphyRepository.getStickersResponse(
            query = SEARCH_QUERY,
            PAGE,
            PAGE_SIZE
        )

        // Assert
        // Assert
        assertThat(response).isInstanceOf(Resource.Success::class.java)
        val data = (response as Resource.Success).data
        assertThat(data).isNotEmpty()
        assertThat(data[0].type).isEqualTo(STICKER)
    }

    companion object {
        const val GIF = "gif"
        const val STICKER = "sticker"

        const val SEARCH_QUERY = "Android"

        const val GIF_SUCCESS_RESPONSE_FILE_NAME = "giphy_gif_response.json"
        const val STICKERS_SUCCESS_RESPONSE_FILE_NAME = "giphy_stickers_response.json"

        const val PAGE = 1
        const val PAGE_SIZE = 20
    }
}
