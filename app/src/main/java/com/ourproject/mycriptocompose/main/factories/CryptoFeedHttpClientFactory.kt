package com.ourproject.mycriptocompose.main.factories

import com.ourproject.mycriptocompose.feed.datasource.remote.CryptoFeedHttpClient
import com.ourproject.mycriptocompose.feed.datasource.remote.CryptoFeedRetrofitHttpClient

class CryptoFeedHttpClientFactory {

    companion object{
        fun createCryptoFeedHttpClient(): CryptoFeedHttpClient {
            return CryptoFeedRetrofitHttpClient(
                CryptoFeedServiceFactory.createCryptoFeedService()
            )
        }
    }
}