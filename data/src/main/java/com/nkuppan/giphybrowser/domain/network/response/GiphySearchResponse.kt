package com.nkuppan.giphybrowser.domain.network.response

import com.google.gson.annotations.SerializedName
import com.nkuppan.giphybrowser.domain.network.model.GiphyImageDto

/**
 * This is GIPHY's Gif or Stickers search API response model class with pagination information
 */
data class GiphySearchResponse(
    @SerializedName("data")
    val data: List<GiphyImageDto>,
    @SerializedName("pagination")
    val pagination: GiphyPaginationResponse,
    @SerializedName("meta")
    val meta: GiphyStatusResponse
) {
    /**
     * @return status of the GIPHY request
     */
    fun isSuccess() = meta.status == 200
}

/**
 * Status response of the GIPHY API. It's a base status response for all the GIPHY's API
 */
data class GiphyStatusResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("response_id")
    val responseId: String
)

/**
 * Common GIPHY's API pagination response
 */
data class GiphyPaginationResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int
)
