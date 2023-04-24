package com.appsample.common.api.di

import com.appsample.common.api.ApiConfigImpl
import com.appsample.common.api.ImageUrlBuilder
import com.appsample.common.api.ImageUrlBuilderImpl
import com.appsample.common.api.RetrofitFactory
import retrofit2.Retrofit

internal interface CommonApiModule {

    val retrofit: Retrofit

    val imageUrlBuilder: ImageUrlBuilder

    class Impl(dependencies: CommonApiDependencies) : CommonApiModule, CommonApiDependencies by dependencies {

        override val retrofit = RetrofitFactory.build(
            ApiConfigImpl,
            credentialsProvider,
        )

        override val imageUrlBuilder get() = ImageUrlBuilderImpl(ApiConfigImpl)
    }
}
