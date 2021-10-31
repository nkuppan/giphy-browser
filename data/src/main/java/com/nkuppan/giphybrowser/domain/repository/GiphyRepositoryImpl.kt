package com.nkuppan.giphybrowser.domain.repository

import android.util.Log
import com.nkuppan.giphybrowser.core.BuildConfig
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.network.GiphyApiService
import com.nkuppan.giphybrowser.domain.network.model.GiphyImageDtoMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class GiphyRepositoryImpl(
    private val service: GiphyApiService,
    private val mapper: GiphyImageDtoMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GiphyRepository {

    override suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Pair<Boolean, List<GiphyImage>> = withContext(dispatcher) {
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

        return@withContext response.isNotEmpty() to mapper.toDomainList(response)
    }

    override suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Pair<Boolean, List<GiphyImage>> = withContext(dispatcher) {
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

        return@withContext response.isNotEmpty() to mapper.toDomainList(response)
    }

    companion object {
        private const val TAG = "GiphyRepository"
    }
}
