package com.nkuppan.giphybrowser.domain.usecase

import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository

class StickerSearchUseCase(
    private val repository: GiphyRepository
) {

    suspend operator fun invoke(
        aQuery: String,
        aPage: Int,
        aPageSize: Int
    ): Pair<Boolean, List<GiphyImage>> {
        return repository.getStickersResponse(aQuery, aPage, aPageSize)
    }
}