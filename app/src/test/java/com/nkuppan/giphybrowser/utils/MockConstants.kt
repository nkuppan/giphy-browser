package com.nkuppan.giphybrowser.utils

import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.GiphyImageAttributes


const val PAGE_SIZE = 2
const val FAKE_ID = "1"
const val FAKE_SEARCH_QUERY = "Android"
const val GIF = "gif"
const val STICKER = "sticker"

private val fakeImageAttribute = GiphyImageAttributes("100", "100", "")

val fakeGifImageObject = GiphyImage(
    FAKE_ID,
    FAKE_SEARCH_QUERY,
    GIF,
    "",
    fakeImageAttribute,
    fakeImageAttribute
)

val fakeStickerImageObject = GiphyImage(
    FAKE_ID,
    FAKE_SEARCH_QUERY,
    STICKER,
    "",
    fakeImageAttribute,
    fakeImageAttribute
)

fun getGiphyGifObject(id: String = FAKE_ID): GiphyImage {
    return GiphyImage(
        id,
        FAKE_SEARCH_QUERY,
        GIF,
        "",
        fakeImageAttribute,
        fakeImageAttribute
    )
}

fun getGiphyStickerObject(id: String = FAKE_ID): GiphyImage {
    return GiphyImage(
        id,
        FAKE_SEARCH_QUERY,
        STICKER,
        "",
        fakeImageAttribute,
        fakeImageAttribute
    )
}