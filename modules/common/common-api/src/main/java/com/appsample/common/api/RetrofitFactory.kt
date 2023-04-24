package com.appsample.common.api

import com.appsample.common.api.auth.AuthInterceptor
import com.appsample.credentials.provider.providers.CredentialsProvider
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


object RetrofitFactory {

    fun build(apiConfig: ApiConfig, credentialsProvider: CredentialsProvider): Retrofit {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val json = Json { ignoreUnknownKeys = true }
        val parser = json.asConverterFactory(MediaType.get("application/json"))

        val client = OkHttpClient()
            .newBuilder()
            .addInterceptor(AuthInterceptor(apiConfig.apiKeyParameter, credentialsProvider))
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(apiConfig.baseUrl)
            .addConverterFactory(parser)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
