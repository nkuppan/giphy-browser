package com.nkuppan.giphybrowser.domain.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class NetworkResult<out R> {

    data class Success<out T>(val data: T) : NetworkResult<T>()

    data class Error(val exception: Exception) : NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [NetworkResult] is of type [NetworkResult.Success] & holds non-null [NetworkResult.Success.data].
 */
val NetworkResult<*>.succeeded
    get() = this is NetworkResult.Success && data != null