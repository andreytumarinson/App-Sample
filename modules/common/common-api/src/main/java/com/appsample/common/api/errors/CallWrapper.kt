package com.appsample.common.api.errors

inline fun <T> callApi(call: () -> T ): T {
    try {
        return call()
    } catch (e: Exception) {
        throw CloudErrorHandler.handleError(e)
    }
}
