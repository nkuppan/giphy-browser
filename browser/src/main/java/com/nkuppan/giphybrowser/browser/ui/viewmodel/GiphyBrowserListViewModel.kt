package com.nkuppan.giphybrowser.browser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nkuppan.giphybrowser.browser.data.GiphyPagingSource
import com.nkuppan.giphybrowser.browser.model.Type
import com.nkuppan.giphybrowser.browser.repository.GiphyRepository
import com.nkuppan.giphybrowser.browser.utils.PAGE_SIZE
import com.nkuppan.giphybrowser.core.domain.model.GiphyImage
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow

open class GiphyBrowserListViewModel(
    aApplication: Application,
    aType: Type,
    aRepository: GiphyRepository
) : BaseViewModel(aApplication) {

    private var queryString: String = ""

    private var giphyPagingSource: GiphyPagingSource = GiphyPagingSource(
        aType, aRepository
    )

    val searchResult: Flow<PagingData<GiphyImage>> = Pager(PagingConfig(PAGE_SIZE)) {
        giphyPagingSource
    }.flow.cachedIn(viewModelScope)

    fun refreshSearchWithQuery(aQuery: String): Boolean {

        val trimmedQuery = aQuery.trim()

        if (trimmedQuery == queryString) {
            return false
        }

        queryString = trimmedQuery

        giphyPagingSource.query = queryString

        return true
    }

    fun updateQuery(aQuery: String) {
        giphyPagingSource.query = aQuery
    }
}
