package com.example.playground.app.common.core.models

sealed class Resultt<out T> {

    data class Success<out T>(val data : T) : Resultt<T>()

    data class Failure(val error: ErrorResponse) : Resultt<Nothing>()

    inline fun fold(success : (T) -> Unit, failure : (ErrorResponse) -> Unit) {
        when (this) {
            is Success -> {
                success(data)
            }
            is Failure -> {
                failure(error)
            }
        }
    }

}