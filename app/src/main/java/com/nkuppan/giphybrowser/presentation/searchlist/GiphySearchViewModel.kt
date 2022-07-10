package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import com.nkuppan.giphybrowser.domain.utils.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * This is a common view model class for the paging search source [PagingData].
 */
abstract class GiphySearchViewModel<T : Any> : BaseViewModel() {

    protected var queryString: String = ""

    private var searchResult: Flow<PagingData<T>> = flow {}

    /**
     * Refresh the pager if the search keyword is different
     */
    fun refreshSearchWithQuery(aQuery: String): Boolean {

        val trimmedQuery = aQuery.trim()

        if (trimmedQuery == queryString) {
            return false
        }

        queryString = trimmedQuery

        searchResult = Pager(PagingConfig(PAGE_SIZE)) { getPagingSource() }
            .flow.cachedIn(viewModelScope)

        return true
    }

    /**
     * This paging source can be anything and this will be used to construct the paging source from
     * client view model [PagingSource].
     */
    abstract fun getPagingSource(): PagingSource<Int, T>

    fun getPagingResult(): Flow<PagingData<T>> {
        return searchResult
    }
}
