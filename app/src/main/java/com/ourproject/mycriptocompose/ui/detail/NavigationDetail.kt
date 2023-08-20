package com.ourproject.mycriptocompose.ui.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ourproject.mycriptocompose.domain.CryptoFeedItem

internal const val nameArg = "name"
const val cryptoDetailsRoute = "crypto_details_rouse/{$nameArg}"


fun NavController.navigateToCryptoDetails(
    name: CryptoFeedItem,
    navOptions: NavOptions? = null
) {
    this.navigate("$cryptoDetailsRoute/${name.coinInfo.name}", navOptions)
}

fun NavGraphBuilder.cryptoDetailScreen(
    popBackStack: () -> Unit
) {
    composable(
        route = "$cryptoDetailsRoute/{$nameArg}",
        arguments = listOf(
            navArgument(nameArg) { type = NavType.StringType }
        )
    ) {
        CryptoDetailsRoute(
            popBackStack = popBackStack,
            name = it.arguments?.getString(nameArg)
        )
    }
}
