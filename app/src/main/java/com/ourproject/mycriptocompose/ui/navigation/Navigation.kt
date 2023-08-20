package com.ourproject.mycriptocompose.ui.navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import androidx.navigation.compose.composable
import com.ourproject.mycriptocompose.domain.CryptoFeedItem
import com.ourproject.mycriptocompose.ui.landing.CryptoFeedRoute


const val cryptoGraphRoute = "crypto_graph_route"
const val cryptoFeedRoute = "crypto_feed_route"

fun NavGraphBuilder.cryptoGraph(
    onCryptoClick: (CryptoFeedItem) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = cryptoGraphRoute,
        startDestination = cryptoFeedRoute
    ) {
        composable(
            route = cryptoFeedRoute
        ) {
            CryptoFeedRoute(onNavigateToCryptoDetails = onCryptoClick)
        }
    }
}