package com.appsample.movies.di

import android.content.Context
import com.appsample.app.di.AppComponentHolder
import com.appsample.common.api.ImageUrlBuilder
import com.appsample.common.api.di.CommonApiComponentHolder
import retrofit2.Retrofit

internal interface MoviesDependencies {

    val retrofit: Retrofit

    val imageUrlBuilder: ImageUrlBuilder

    val context: Context

    class Impl : MoviesDependencies {
        override val retrofit get() = CommonApiComponentHolder.get().retrofit
        override val imageUrlBuilder get() = CommonApiComponentHolder.get().imageUrlBuilder
        override val context get() = AppComponentHolder.get().context
    }
}
