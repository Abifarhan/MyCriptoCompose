package com.ourproject.mycriptocompose.ui.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun CryptoDetailsRoute(
    name: String?,
    popBackStack: () -> Unit
) {
    CryptoDetailsScreen(
        name = name,
        popBackStack = popBackStack
    )
}

@Composable
fun CryptoDetailsScreen(
    name: String?,
    popBackStack: () -> Unit
) {

}