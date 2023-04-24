package com.appsample.credentials.provider.di

import com.appsample.common.di.BaseComponentHolder
import com.appsample.common.di.Component
import com.appsample.credentials.provider.providers.CredentialsProvider


interface CredentialsProviderComponent : Component {

    val credentialsProvider: CredentialsProvider
}

internal class CredentialsProviderComponentImpl(
    private val module: CredentialsProviderModule = CredentialsProviderModule.Impl()
) : CredentialsProviderComponent {

    override val credentialsProvider get() = module.credentialsProvider
}


object CredentialsProviderComponentHolder : BaseComponentHolder<CredentialsProviderComponent>() {

    override fun build(): CredentialsProviderComponent = CredentialsProviderComponentImpl()
}
