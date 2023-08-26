package com.ourproject.mycriptocompose.feed.datasource.remote

import com.ourproject.mycriptocompose.domain.CryptoFeedLoader
import com.ourproject.mycriptocompose.main.factories.CryptoFeedHttpClientFactory

class RemoteCryptoFeedLoaderFactory {

    companion object{
        fun createRemoteCryptoFeedLoader(): CryptoFeedLoader {
            return RemoteCryptoFeedLoaderUseCase(
                CryptoFeedHttpClientFactory.createCryptoFeedHttpClient()
            )
        }
    }
}