package com.appsample.credentials.provider.providers

import com.appsample.credentials.provider.TestData

internal class CredentialsProviderImpl: CredentialsProvider {

    override fun getApiKey(): String {
        //todo In the real app can loaded from secure storage
        return TestData.TEST_API_KEY
    }
}
