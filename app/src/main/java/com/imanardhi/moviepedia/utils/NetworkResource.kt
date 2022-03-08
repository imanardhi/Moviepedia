package com.imanardhi.moviepedia.utils

sealed class NetworkResource<out T> {
    data class Success<out T>(val value: T) : NetworkResource<T>()

    data class Failure(val isNetworkError: Boolean, val errorCode: Int?, val errorBody: String?) :
        NetworkResource<Nothing>()

    object Loading : NetworkResource<Nothing>()
}