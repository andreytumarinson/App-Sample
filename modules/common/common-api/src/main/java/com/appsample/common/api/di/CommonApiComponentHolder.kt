package com.appsample.common.api.di

import com.appsample.common.api.ImageUrlBuilder
import com.appsample.common.di.BaseComponentHolder
import com.appsample.common.di.Component
import retrofit2.Retrofit

interface CommonApiComponent : Component {

    val retrofit: Retrofit

    val imageUrlBuilder: ImageUrlBuilder
}

internal class CommonApiComponentImpl(
    dependencies: CommonApiDependencies = CommonApiDependencies.Impl(),
    private val module: CommonApiModule = CommonApiModule.Impl(dependencies)
) : CommonApiComponent {

    override val retrofit get() = module.retrofit

    override val imageUrlBuilder get() = module.imageUrlBuilder
}

object CommonApiComponentHolder : BaseComponentHolder<CommonApiComponent>() {

    override fun build(): CommonApiComponent = CommonApiComponentImpl()
}
