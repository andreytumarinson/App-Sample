package com.appsample.common.api

interface ApiConfig {
    val baseUrl : String

    val imageBaseUrl : String

    val apiKeyParameter  : String
}

internal object ApiConfigImpl: ApiConfig {

    override val baseUrl = "https://api.themoviedb.org/"
    override val imageBaseUrl = "https://image.tmdb.org/t/p/w500"
    override val apiKeyParameter = "api_key"
}
