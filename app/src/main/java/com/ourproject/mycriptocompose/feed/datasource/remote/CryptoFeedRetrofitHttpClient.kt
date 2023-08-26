package com.ourproject.mycriptocompose.feed.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.io.IOException


class CryptoFeedRetrofitHttpClient constructor(
    private val cryptoFeedService: CryptoFeedService
) : CryptoFeedHttpClient {


    override fun get(): Flow<HttpClientResult> {
        return flow {
            try {
                emit(HttpClientResult.Success(cryptoFeedService.get()))
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        emit(HttpClientResult.Failure(ConnectivityException()))
                    }
                    is HttpException -> {
                        if (t.code() == 422) {
                            emit(HttpClientResult.Failure(InvalidDataException()))
                        }
                    }
                    else -> {
                        emit(HttpClientResult.Failure(InvalidDataException()))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}