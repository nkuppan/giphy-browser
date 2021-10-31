package com.nkuppan.giphybrowser.domain.network.model

import com.google.gson.annotations.SerializedName

/**
 * GIPHY's images details and different formats image source
 */
data class GiphyImageDto(
    val id: String,
    val title: String,
    val type: String,
    val url: String,
    val rating: String,
    val source: String,
    val images: GiphyImagesListDto
)

/**
 * GIPHY's images list with different formats
 */
data class GiphyImagesListDto(
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
    val height: String,
    val width: String,
    val size: String,
    val url: String
)
