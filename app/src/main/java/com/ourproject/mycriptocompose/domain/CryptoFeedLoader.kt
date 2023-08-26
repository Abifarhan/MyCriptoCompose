package com.ourproject.mycriptocompose.domain

import kotlinx.coroutines.flow.Flow


sealed class CryptoFeedResult {
    data class Success(val cryptoFeedItems: List<CryptoFeedItem>): CryptoFeedResult()

    data class Failure(val throwable: Throwable): CryptoFeedResult()
}


interface CryptoFeedLoader {

    fun load(): Flow<CryptoFeedResult>
}


class InvalidData : Throwable()
class Connectivity: Throwable()
