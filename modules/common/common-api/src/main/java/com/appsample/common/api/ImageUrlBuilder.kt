package com.appsample.common.api


interface ImageUrlBuilder {

    fun buildUrl(urlPostfix: String): String
}

internal class ImageUrlBuilderImpl(
    private val appConfig: ApiConfig,
) : ImageUrlBuilder {

    override fun buildUrl(urlPostfix: String): String {
        return appConfig.imageBaseUrl + urlPostfix
    }
}
