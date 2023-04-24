package com.appsample.movies.ui.utils

import kotlinx.coroutines.CancellationException

/** Ignores CancellationException and doesn't send it to onFailure**/
inline fun <R> runCatchingNonCancellation(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}
