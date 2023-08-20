package com.ourproject.mycriptocompose.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ourproject.mycriptocompose.ui.detail.cryptoDetailScreen
import com.ourproject.mycriptocompose.ui.detail.navigateToCryptoDetails
import com.ourproject.mycriptocompose.ui.navigation.cryptoGraph
import com.ourproject.mycriptocompose.ui.navigation.cryptoGraphRoute

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = cryptoGraphRoute
) {

    NavHost(
        navController = navHostController,
        modifier = modifier,
        startDestination = startDestination
    ) {
        cryptoGraph(
            onCryptoClick = navHostController::navigateToCryptoDetails
        ) {
            cryptoDetailScreen {

            }
        }
    }
}