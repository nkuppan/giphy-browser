package com.nkuppan.giphybrowser.domain.network.model

import com.google.gson.annotations.SerializedName
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.GiphyImageAttributes

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
) {
    fun toGiphyImage(): GiphyImage {
        return GiphyImage(
            id = id,
            title = title,
            type = type,
            url = url,
            original = images.original.toGiphyImageAttributes(),
            thumbnail = images.fixedHeight.toGiphyImageAttributes()
        )
    }
}

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
) {
    fun toGiphyImageAttributes(): GiphyImageAttributes {
        return GiphyImageAttributes(
            height = height,
            width = width,
            url = url
        )
    }
}
