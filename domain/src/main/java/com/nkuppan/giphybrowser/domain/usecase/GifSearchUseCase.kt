package com.nkuppan.giphybrowser.domain.usecase

import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.NetworkResult
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository

/**
 * This use case is a wrapper to call between repository and viewmodel. As per the clean
 * architecture. We should segregate the business logics into domain layer. Call that through
 * --------------------------------------------------------------------------
 * |    UI --> ViewModel --> Use cases --> Repository --> Framework logics  |
 * -------------------------------------------------------------------------
 * This use cases will do a single business logics. This kind of separation will be useful for
 * bigger projects. All business logics will go through use case abstraction.
 *
 * @param repository will carry the abstraction of the framework layer
 */
class GifSearchUseCase(
    private val repository: GiphyRepository
) {

    suspend operator fun invoke(
        aQuery: String,
        aPage: Int,
        aPageSize: Int
    ): NetworkResult<List<GiphyImage>> {
        return repository.getGifResponse(aQuery, aPage, aPageSize)
    }
}