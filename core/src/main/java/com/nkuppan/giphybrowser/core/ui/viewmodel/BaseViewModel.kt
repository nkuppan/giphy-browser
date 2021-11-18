package com.nkuppan.giphybrowser.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Wrapper viewmodel class and this will be inherited by all viewmodel in the project
 */
open class BaseViewModel : ViewModel() {

    protected val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int> = _errorMessage
}
