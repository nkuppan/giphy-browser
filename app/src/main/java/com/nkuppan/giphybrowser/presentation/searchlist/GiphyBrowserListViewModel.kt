package com.nkuppan.giphybrowser.presentation.searchlist

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.domain.repository.GiphyRepository
import com.nkuppan.giphybrowser.domain.source.GiphyPagingSource
import com.nkuppan.giphybrowser.domain.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class GiphyBrowserListViewModel(
    aApplication: Application,
    private val aType: Type,
    private val aRepository: GiphyRepository
) : BaseViewModel(aApplication) {

    private var queryString: String = ""

    var searchResult: Flow<PagingData<GiphyImage>> = flow {}
        private set

    fun refreshSearchWithQuery(aQuery: String): Boolean {

        val trimmedQuery = aQuery.trim()

        if (trimmedQuery == queryString) {
            return false
        }

        queryString = trimmedQuery

        searchResult = Pager(PagingConfig(PAGE_SIZE)) {
            GiphyPagingSource(
                aType, queryString, aRepository
            )
        }.flow.cachedIn(viewModelScope)

        return true
    }
}
