package com.nkuppan.giphybrowser.domain.utils

fun String?.isValidQueryString(): Boolean {

    if (isNullOrBlank()) {
        return false
    }

    if (trim().length < MAX_QUERY_LENGTH) {
        return false
    }

    return true
}
