package com.appsample.common.api.errors

import com.appsample.common.models.Error
import com.appsample.common.models.AppException
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

/**
 * Should provide common https errors processing to app's events in the one place
 * Could be used in all cloud calls where is necessary
 */
object CloudErrorHandler {

    fun handleError(e: Exception): Exception {
        return when (e) {
            is IOException -> AppException(Error.NO_CONNECTION)
            is HttpException -> AppException(handleHttpError(e))
            is CancellationException -> e
            else -> AppException(Error.UNKNOWN_ERROR)
        }
    }

    private fun handleHttpError(e: HttpException): Error {
        return when (e.code()) {
            401 -> Error.AUTH_ERROR
            else -> Error.SERVER_ERROR
        }
    }
}


