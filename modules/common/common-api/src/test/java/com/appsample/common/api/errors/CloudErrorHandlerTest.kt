package com.appsample.common.api.errors

import com.appsample.common.models.AppException
import com.appsample.common.models.Error
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CancellationException
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class HttpErrorHandlerUnitTest {

    @Test
    fun parseNoConnection() {
        val error = CloudErrorHandler.handleError(IOException())
        assertEquals(AppException(Error.NO_CONNECTION), error)
    }

    @Test
    fun parse401() {
        val ex = HttpException(Response.error<ResponseBody>(401 ,ResponseBody.create(MediaType.parse("plain/text"),"test content")))
        val error = CloudErrorHandler.handleError(ex)
        assertEquals(AppException(Error.AUTH_ERROR), error)
    }

    @Test
    fun parseUnknownCode() {
        val ex = HttpException(Response.error<ResponseBody>(500 ,ResponseBody.create(MediaType.parse("plain/text"),"test content")))
        val error = CloudErrorHandler.handleError(ex)
        assertEquals(AppException(Error.SERVER_ERROR), error)
    }

    @Test
    fun parseUnknownException() {
        val error = CloudErrorHandler.handleError(Exception())
        assertEquals(AppException(Error.UNKNOWN_ERROR), error)
    }

    @Test
    fun checkCancelException() {
        val ex = CancellationException()
        assertEquals(ex, CloudErrorHandler.handleError(ex))
    }
}
