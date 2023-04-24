package com.appsample.common.api.di

import com.appsample.credentials.provider.di.CredentialsProviderComponentHolder
import com.appsample.credentials.provider.providers.CredentialsProvider

internal interface CommonApiDependencies {

    val credentialsProvider: CredentialsProvider

    class Impl : CommonApiDependencies {
        override val credentialsProvider get() = CredentialsProviderComponentHolder.get().credentialsProvider
    }
}
