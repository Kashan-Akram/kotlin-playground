package com.example.playground.app.common.core.models

data class ErrorResponse(
    val errorCode: ErrorCode,
    val message: String
) {

    constructor(errorCode: ErrorCode) : this(errorCode, "")

    enum class ErrorCode {
        NETWORK_UNAVAILABLE,
        UNSUCCESSFUL_SERVER_RESPONSE,
        NETWORK_TIMEOUT,
        UNAUTHORISED,
        FORBIDDEN,
        NOT_FOUND,
        TOO_MANY_REQUESTS,
        INVALID_TOKEN,
        INVALID_RESPONSE,
        UNKNOWN_ERROR,
        UNSUCCESSFUL_DB_ACTION
    }

}