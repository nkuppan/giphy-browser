package com.nkuppan.giphybrowser.domain.network

import com.nkuppan.giphybrowser.domain.network.response.GiphySearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyApiService {

    /**
     * Using this Retrofit function we will contact the GIPHY gif search with specified params.
     *
     * @param version can be modified in the future either that can be send it from caller
     * @param apiKey will passed from the caller (*)
     * @param query gif search will depends on this call (*)
     * @param limit will restrict the api data size and default limit will be 25
     * @param offset will tell you the starting position of the search data
     * @param rating rating of the gif search {RATING_G, RATING_PG, RATING_PG_13, RATING_R}
     * @param language Giphy is supporting multiple languages as of now we are keeping english here
     */
    @GET("{version}/gifs/search")
    fun searchGiphyGifs(
        @Path("version") version: String = API_VERSION,
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("offset") offset: Int = DEFAULT_OFFSET,
        @Query("rating") rating: String = RATING_G,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<GiphySearchResponse>

    /**
     * Using this Retrofit function we will contact the GIPHY stickers search with specified params.
     *
     * @param version can be modified in the future either that can be send it from caller
     * @param apiKey will passed from the caller (*)
     * @param query gif search will depends on this call (*)
     * @param limit will restrict the api data size and default limit will be 25
     * @param offset will tell you the starting position of the search data
     * @param rating rating of the gif search {RATING_G, RATING_PG, RATING_PG_13, RATING_R}
     * @param language Giphy is supporting multiple languages as of now we are keeping english here
     */
    @GET("{version}/stickers/search")
    fun searchGiphyStickers(
        @Path("version") version: String = API_VERSION,
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("offset") offset: Int = DEFAULT_OFFSET,
        @Query("rating") rating: String = RATING_G,
        @Query("language") language: String = LANGUAGE_EN
    ): Call<GiphySearchResponse>

    companion object {
        const val DEFAULT_LIMIT = 20
        const val DEFAULT_OFFSET = 0

        const val API_VERSION = "v1"
        const val LANGUAGE_EN = "en"

        const val RATING_G = "g"
        const val RATING_PG = "pg"
        const val RATING_PG_13 = "pg-13"
        const val RATING_R = "r"

        const val BASE_URL = "https://api.giphy.com/"
    }
}
