package com.nkuppan.giphybrowser.data.repository

import com.nkuppan.giphybrowser.data.BuildConfig
import com.nkuppan.giphybrowser.data.network.GiphyApiService
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Resource
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.util.logging.Logger

class GiphyRepositoryImpl(
    private val service: GiphyApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : GiphyRepository {

    override suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<GiphyImage>> = withContext(dispatcher) {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyGifs(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                Resource.Success(
                    (response.body()?.data ?: emptyList()).map { it.toGiphyImage() }
                )
            } else {
                Resource.Error(KotlinNullPointerException("No data found"))
            }
        } catch (e: Exception) {
            Logger.getGlobal().severe(e.toString())
            Resource.Error(e)
        }

        return@withContext response
    }

    override suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<GiphyImage>> = withContext(dispatcher) {
        val response = try {
            val apiKey = BuildConfig.GIPHY_API_KEY
            val response = service.searchGiphyStickers(
                apiKey = apiKey,
                query = query,
                offset = page,
                limit = pageSize
            ).awaitResponse()
            if (response.isSuccessful && response.body()?.isSuccess() == true) {
                Resource.Success(
                    (response.body()?.data ?: emptyList()).map { it.toGiphyImage() }
                )
            } else {
                Resource.Error(KotlinNullPointerException("No data found"))
            }
        } catch (e: Exception) {
            Logger.getGlobal().severe(e.toString())
            Resource.Error(e)
        }

        return@withContext response
    }
}
