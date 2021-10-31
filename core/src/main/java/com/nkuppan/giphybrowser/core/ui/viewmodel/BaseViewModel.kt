package com.nkuppan.giphybrowser.core.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Wrapper viewmodel class and this will be inherited by all viewmodel in the project
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
}
