package com.appsample.common.models

enum class Error {
    NO_CONNECTION,
    NO_CONTENT,
    AUTH_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR,
}

data class AppException(val error: Any): Exception()
