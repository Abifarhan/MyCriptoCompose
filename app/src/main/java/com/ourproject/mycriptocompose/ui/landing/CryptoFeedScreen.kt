package com.ourproject.mycriptocompose.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.ourproject.mycriptocompose.domain.CryptoFeedItem


@Composable
fun CryptoFeedRoute(
//    viewModel: CryptoFeedViewModel = viewM
    onNavigateToCryptoDetails: (CryptoFeedItem) -> Unit
) {

    CryptoFeedScreen(

    )

}

@Composable
fun CryptoFeedScreen(
    modifier: Modifier = Modifier,
//    onRefreshCryptoFeed: () -> Unit? = null,
//    onNavigateToCryptoDetails: (CryptoFeedItem) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material.Text(
            text = "Hello, Jetpack Compose!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
