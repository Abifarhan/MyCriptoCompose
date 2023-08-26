package com.ourproject.mycriptocompose.feed.datasource.remote

import com.ourproject.mycriptocompose.domain.CryptoFeedItemsMapper
import com.ourproject.mycriptocompose.domain.CryptoFeedLoader
import com.ourproject.mycriptocompose.domain.CryptoFeedResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCryptoFeedLoaderUseCase constructor(
    private val cryptoFeedHttpClient: CryptoFeedHttpClient
) : CryptoFeedLoader {


    override fun load(): Flow<CryptoFeedResult> {
        return flow {
            cryptoFeedHttpClient.get().collect{result ->
                when (result) {
                    is HttpClientResult.Success -> {
                        val cryptoFeed = result.root.data

                        if (cryptoFeed.isNotEmpty()) {
                            val cryptoFeedRemote = CryptoFeedItemsMapper.map(cryptoFeed)
                            emit(CryptoFeedResult.Success(cryptoFeedRemote))
                        }
                    }

                    is HttpClientResult.Failure -> {

                    }
                }
            }
        }
    }


}