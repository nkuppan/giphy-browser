package com.nkuppan.giphybrowser.domain.repository

import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.NetworkResult

interface GiphyRepository {

    /**
     * Reading a paged data from the GIPHY gif api
     */
    suspend fun getGifResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>>

    /**
     * Reading a paged data from the GIPHY stickers api
     */
    suspend fun getStickersResponse(
        query: String,
        page: Int,
        pageSize: Int
    ): NetworkResult<List<GiphyImage>>
}
