package com.example.playground.app.common.core.network

import com.example.playground.app.common.core.models.ErrorResponse
import com.example.playground.app.common.core.models.Resultt
import retrofit2.HttpException
import java.net.SocketTimeoutException

suspend fun<T> networkBoundOperation(
    networkCall : suspend () -> T
) : Resultt<T> {
    return runCatching {
        networkCall.invoke()
    }.map { data ->
        Resultt.Success(data)
    }.getOrElse { exception ->
        when (exception) {
            is SocketTimeoutException -> {
                Resultt.Failure(
                    error = ErrorResponse(
                        errorCode = ErrorResponse.ErrorCode.NETWORK_TIMEOUT
                    )
                )
            }
            is HttpException -> {
                when (exception.code()) {
                    401 -> {
                        Resultt.Failure(
                            error = ErrorResponse(
                                errorCode = ErrorResponse.ErrorCode.UNAUTHORISED
                            )
                        )
                    }
                    403 -> {
                        Resultt.Failure(
                            error = ErrorResponse(
                                errorCode = ErrorResponse.ErrorCode.FORBIDDEN
                            )
                        )
                    }
                    404 -> {
                        Resultt.Failure(
                            error = ErrorResponse(
                                errorCode = ErrorResponse.ErrorCode.NOT_FOUND
                            )
                        )
                    }
                    else -> {
                        Resultt.Failure(
                            error = ErrorResponse(
                                errorCode = ErrorResponse.ErrorCode.UNKNOWN_ERROR,
                                message = exception.message.toString()
                            )
                        )
                    }
                }
            }
            else -> {
                Resultt.Failure(
                    error = ErrorResponse(
                        errorCode = ErrorResponse.ErrorCode.UNSUCCESSFUL_SERVER_RESPONSE,
                        message = exception.message.toString()
                    )
                )
            }
        }
    }
}