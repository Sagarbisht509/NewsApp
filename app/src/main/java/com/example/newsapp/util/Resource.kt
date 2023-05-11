package com.example.newsapp.util

sealed class Resource<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>: Resource<T>()

    class Success<T> (d: T? = null): Resource<T>(data = d)

    class Error<T> (msg : String): Resource<T>(message = msg)
}
