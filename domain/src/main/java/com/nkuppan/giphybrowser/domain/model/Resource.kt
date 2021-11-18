package com.nkuppan.giphybrowser.domain.model

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()

    data class Error(val exception: Exception) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [Resource] is of type [Resource.Success] & holds non-null [Resource.Success.data].
 */
val Resource<*>.succeeded
    get() = this is Resource.Success && data != null