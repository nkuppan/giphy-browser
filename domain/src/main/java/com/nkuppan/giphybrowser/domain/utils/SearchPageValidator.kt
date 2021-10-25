package com.nkuppan.giphybrowser.domain.utils

fun String?.isValidQueryString(): Boolean {

    if (isNullOrBlank()) {
        return false
    }

    if (trim().length < 3) {
        return false
    }

    return true
}
