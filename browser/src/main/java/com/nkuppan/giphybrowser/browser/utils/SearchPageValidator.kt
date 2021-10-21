package com.nkuppan.giphybrowser.browser.utils

fun String?.isValidQueryString(): Boolean {

    if (isNullOrBlank()) {
        return false
    }

    if (trim().length < 3) {
        return false
    }

    return true
}
