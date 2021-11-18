package com.nkuppan.giphybrowser.domain.repository

import com.nkuppan.giphybrowser.core.BuildConfig
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.NetworkResult
import com.nkuppan.giphybrowser.domain.network.GiphyApiService
import com.nkuppan.giphybrowser.domain.network.model.GiphyImageDtoMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.logging.Logger

class GiphyRepositoryImpl(
    private val service: GiphyApiService,
    private val mapper: GiphyImageDtoMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GiphyRepository {

    override suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>> = withContext(dispatcher) {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyGifs(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                NetworkResult.Success(mapper.toDomainList(response.body()?.data ?: emptyList()))
            } else {
                NetworkResult.Error(KotlinNullPointerException("No data found"))
            }
        } catch (e: Exception) {
            Logger.getGlobal().severe(e.toString())
            NetworkResult.Error(e)
        }

        return@withContext response
    }

    override suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>> = withContext(dispatcher) {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyStickers(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                NetworkResult.Success(mapper.toDomainList(response.body()?.data ?: emptyList()))
            } else {
                NetworkResult.Error(KotlinNullPointerException("No data found"))
            }
        } catch (e: Exception) {
            Logger.getGlobal().severe(e.toString())
            NetworkResult.Error(e)
        }

        return@withContext response
    }
}
