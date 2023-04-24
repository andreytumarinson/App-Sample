package com.appsample.credentials.provider.di

import com.appsample.credentials.provider.providers.CredentialsProvider
import com.appsample.credentials.provider.providers.CredentialsProviderImpl

internal interface CredentialsProviderModule {

    val credentialsProvider: CredentialsProvider

    class Impl : CredentialsProviderModule{

        override val credentialsProvider get() = CredentialsProviderImpl()
    }
}
