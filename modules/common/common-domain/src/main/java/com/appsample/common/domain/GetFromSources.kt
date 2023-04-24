package com.appsample.common.domain

import com.appsample.common.api.errors.callApi
import com.appsample.common.models.AppException
import com.appsample.common.models.Error

inline fun <T> getFromSources(
    fromServerCall: () -> T,
    fromDbCall: () -> T?,
    toDbCall: (T) -> Unit,
): T {
    try {
        val response = callApi { fromServerCall() }
        toDbCall(response)
    } catch (e: Exception) {
        return fromDbCall() ?: throw e
    }
    return fromDbCall() ?: throw AppException(Error.NO_CONTENT)
}
