package com.nkuppan.giphybrowser.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.Event
import com.nkuppan.giphybrowser.core.ui.viewmodel.BaseViewModel
import com.nkuppan.giphybrowser.domain.utils.isValidQueryString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel() {

    val queryString: MutableLiveData<String> = MutableLiveData()

    private val _searchThisQuery = MutableLiveData<Event<String>>()
    val searchThisQuery: LiveData<Event<String>> = _searchThisQuery

    fun processQuery(): Boolean {

        val query = queryString.value

        return if (query.isValidQueryString()) {
            _searchThisQuery.value = Event(query!!.trim())
            true
        } else {
            _errorMessage.value = R.string.enter_valid_query_string
            false
        }
    }
}
