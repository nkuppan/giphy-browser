package com.nkuppan.giphybrowser.domain.network.model

import com.google.gson.annotations.SerializedName

/**
 * GIPHY's images details and different formats image source
 */
data class GiphyImageDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("images")
    val images: GiphyImagesListDto
)

/**
 * GIPHY's images list with different formats
 */
data class GiphyImagesListDto(
    @SerializedName("original")
    val original: GiphyImageAttributesDto,
    @SerializedName("fixed_height")
    val fixedHeight: GiphyImageAttributesDto,
    @SerializedName("fixed_width")
    val fixedWidth: GiphyImageAttributesDto,
    @SerializedName("fixed_width_small")
    val fixedWidthSmall: GiphyImageAttributesDto,
    @SerializedName("fixed_height_small")
    val fixedHeightSmall: GiphyImageAttributesDto
)

/**
 * GIPHY's image object response
 */
data class GiphyImageAttributesDto(
    @SerializedName("height")
    val height: String,
    @SerializedName("width")
    val width: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("url")
    val url: String
)
