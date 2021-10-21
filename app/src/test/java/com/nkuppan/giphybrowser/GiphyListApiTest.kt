package com.nkuppan.giphybrowser

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nkuppan.giphybrowser.core.network.GiphyApiService
import com.nkuppan.giphybrowser.utils.MockResponseFileReader
import junit.framework.Assert.*
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class GiphyListApiTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    private lateinit var giphyApiService: GiphyApiService

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        mockWebServer = MockWebServer()
        mockWebServer.start()

        giphyApiService = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GiphyApiService.BASE_URL)
            .build().create()
    }

    private fun setupMockResponse(aResponseFileName: String) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader(aResponseFileName).content)
        mockWebServer.enqueue(response)
    }

    @Test
    fun `read giphy success json response file`() {
        val reader = MockResponseFileReader(GIF_SUCCESS_RESPONSE_FILE_NAME)
        assertNotNull(reader.content)
    }

    @Test
    fun `fetch giphy gifs and check response Code 200 returned`() {
        // Assign
        setupMockResponse(GIF_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val actualResponse = giphyApiService.searchGiphyGifs(
            apiKey = com.nkuppan.giphybrowser.core.BuildConfig.GIPHY_API_KEY,
            query = "Android"
        ).execute()

        val apiResponse = actualResponse.body()
        // Assert
        assertEquals(200, apiResponse?.meta?.status ?: -1)

        assertTrue(apiResponse?.data?.isNotEmpty() ?: false)
    }

    @Test
    fun `fetch giphy gifs and check response value and type returned`() {
        // Assign
        setupMockResponse(GIF_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val actualResponse = giphyApiService.searchGiphyGifs(
            apiKey = com.nkuppan.giphybrowser.core.BuildConfig.GIPHY_API_KEY,
            query = "Android"
        ).execute()

        val apiResponse = actualResponse.body()
        // Assert
        assertEquals(200, apiResponse?.meta?.status ?: -1)

        assertTrue(apiResponse?.data?.isNotEmpty() ?: false)

        assertEquals("gif", apiResponse?.data?.get(0)?.type)
    }

    @Test
    fun `fetch giphy stickers and check response Code 200 returned`() {
        // Assign
        setupMockResponse(STICKERS_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val actualResponse = giphyApiService.searchGiphyStickers(
            apiKey = com.nkuppan.giphybrowser.core.BuildConfig.GIPHY_API_KEY,
            query = "Android"
        ).execute()

        val apiResponse = actualResponse.body()
        // Assert
        assertEquals(200, apiResponse?.meta?.status ?: -1)

        assertTrue(apiResponse?.data?.isNotEmpty() ?: false)
    }

    @Test
    fun `fetch giphy stickers and check response value and type returned`() {
        // Assign
        setupMockResponse(STICKERS_SUCCESS_RESPONSE_FILE_NAME)
        // Act
        val actualResponse = giphyApiService.searchGiphyStickers(
            apiKey = com.nkuppan.giphybrowser.core.BuildConfig.GIPHY_API_KEY,
            query = "Android"
        ).execute()

        val apiResponse = actualResponse.body()
        // Assert
        assertEquals(200, apiResponse?.meta?.status ?: -1)

        assertTrue(apiResponse?.data?.isNotEmpty() ?: false)

        assertEquals("sticker", apiResponse?.data?.get(0)?.type)
    }

    companion object {
        const val GIF_SUCCESS_RESPONSE_FILE_NAME = "giphy_gif_response.json"
        const val STICKERS_SUCCESS_RESPONSE_FILE_NAME = "giphy_stickers_response.json"
    }
}
