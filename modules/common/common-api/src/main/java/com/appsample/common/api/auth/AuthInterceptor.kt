package com.appsample.common.api.auth

import com.appsample.credentials.provider.providers.CredentialsProvider
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(
    private val apiKeyParameter: String,
    credentialsProvider: CredentialsProvider,
) : Interceptor {
    private val apiKey = credentialsProvider.getApiKey();

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url()
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter(apiKeyParameter, apiKey).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}
