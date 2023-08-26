package com.ourproject.mycriptocompose.main.factories

import com.ourproject.mycriptocompose.feed.datasource.remote.CryptoFeedService
import com.ourproject.mycriptocompose.frameworks.RemoteFactory

class CryptoFeedServiceFactory {

    companion object{
        fun createCryptoFeedService(): CryptoFeedService {
            return RemoteFactory.createRetrofit(
                RemoteFactory.createMoshi(),
                RemoteFactory.createOkhttpClient(
                    RemoteFactory.createLoggingInterceptor()
                )
            ).create(CryptoFeedService::class.java)
        }
    }
}