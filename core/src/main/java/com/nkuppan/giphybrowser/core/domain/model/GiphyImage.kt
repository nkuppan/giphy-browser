package com.nkuppan.giphybrowser.core.domain.model

import java.io.Serializable

/**
 * Converted Giphy image response to UI model
 */
data class GiphyImage(
    val id: String,
    val title: String,
    val type: String,
    val url: String,
    val original: GiphyImageAttributes,
    val thumbnail: GiphyImageAttributes
) : Serializable

/**
 * Converted Giphy image attributes response to UI model
 */
data class GiphyImageAttributes(
    val height: String,
    val width: String,
    val url: String
) : Serializable
