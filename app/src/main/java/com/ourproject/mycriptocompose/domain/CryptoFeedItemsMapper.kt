package com.ourproject.mycriptocompose.domain

import com.ourproject.mycriptocompose.feed.datasource.remote.RemoteCryptoFeedItem

class CryptoFeedItemsMapper {

    companion object{
        fun map(items: List<RemoteCryptoFeedItem>): List<CryptoFeedItem> {
            return items.map {
                CryptoFeedItem(
                    coinInfo = CoinInfoItem(
                        id = it.remoteCoinInfo.id.orEmpty(),
                        name = it.remoteCoinInfo.name.orEmpty(),
                        fullName = it.remoteCoinInfo.fullName.orEmpty(),
                        imageUrl = it.remoteCoinInfo.imageUrl.orEmpty()
                    ),
                    raw = RawItem(
                        usd = UsdItem(
                            price = it.remoteRaw.usd.price ?: 0.0,
                            changePctDay = it.remoteRaw.usd.changePctDay ?: 0F
                        )
                    )
                )
            }
        }
    }
}