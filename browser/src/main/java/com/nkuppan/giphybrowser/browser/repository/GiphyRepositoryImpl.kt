package com.nkuppan.giphybrowser.browser.repository

import android.util.Log
import com.nkuppan.giphybrowser.core.BuildConfig
import com.nkuppan.giphybrowser.core.domain.model.GiphyImage
import com.nkuppan.giphybrowser.core.network.GiphyApiService
import com.nkuppan.giphybrowser.core.network.model.GiphyImageDtoMapper
import retrofit2.awaitResponse

class GiphyRepositoryImpl(
    private val service: GiphyApiService,
    private val mapper: GiphyImageDtoMapper
) : GiphyRepository {

    override suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Pair<Boolean, List<GiphyImage>> {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyGifs(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                (response.body()?.data ?: emptyList())
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            emptyList()
        }

        return response.isNotEmpty() to mapper.toDomainList(response)
    }

    override suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Pair<Boolean, List<GiphyImage>> {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyStickers(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                (response.body()?.data ?: emptyList())
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            emptyList()
        }

        return response.isNotEmpty() to mapper.toDomainList(response)
    }

    companion object {
        private const val TAG = "GiphyRepository"
    }
}