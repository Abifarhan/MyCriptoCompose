package com.ourproject.mycriptocompose.feed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ourproject.mycriptocompose.domain.Connectivity
import com.ourproject.mycriptocompose.domain.CryptoFeedItem
import com.ourproject.mycriptocompose.domain.CryptoFeedLoader
import com.ourproject.mycriptocompose.domain.CryptoFeedResult
import com.ourproject.mycriptocompose.domain.InvalidData
import com.ourproject.mycriptocompose.feed.datasource.remote.RemoteCryptoFeedLoaderFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


sealed interface CryptoFeedUiState {
    val isLoading: Boolean
    val failed: String

    data class HasCryptoFeed(
        override val isLoading: Boolean,
        val cryptoFeeds: List<CryptoFeedItem>,
        override val failed: String
    ) : CryptoFeedUiState

    data class NoCryptoFeed(
        override val isLoading: Boolean,
        override val failed: String
    ): CryptoFeedUiState


}

data class CryptoFeedViewModelState(
    val isLoading: Boolean = false,
    val cryptoFeeds: List<CryptoFeedItem> = emptyList(),
    val failed: String = ""
) {
    fun toCryptoFeedUiState(): CryptoFeedUiState =
        if (cryptoFeeds.isEmpty()) {
            CryptoFeedUiState.NoCryptoFeed(
                isLoading = isLoading,
                failed = failed
            )
        } else {
            CryptoFeedUiState.HasCryptoFeed(
                isLoading = isLoading,
                cryptoFeeds = cryptoFeeds,
                failed = failed
            )
        }
}
open class CryptoFeedViewModel constructor(
    private val cryptoFeedLoader: CryptoFeedLoader
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        CryptoFeedViewModelState(
            isLoading = false,
            failed = "Failed get data"
        )
    )

    val cryptoFeedUiState = viewModelState.map(CryptoFeedViewModelState::toCryptoFeedUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewModelState.value.toCryptoFeedUiState()
        )


    init {
        loadCryptoFeed()
    }

    fun loadCryptoFeed() {
        viewModelScope.launch {
            cryptoFeedLoader.load().collect {result ->
                viewModelState.update {
                    when (result) {
                        is CryptoFeedResult.Success -> {
                            it.copy(
                                cryptoFeeds = result.cryptoFeedItems,
                                isLoading = false
                            )
                        }

                        is CryptoFeedResult.Failure -> {
                            it.copy(
                                failed = when (result.throwable) {
                                    is Connectivity -> "Connectivity"
                                    is InvalidData -> "Invalid Data"
                                    else -> "Something Went Wrong"
                                },
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    companion object{
        val FACTORY: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CryptoFeedViewModel(
                    RemoteCryptoFeedLoaderFactory.createRemoteCryptoFeedLoader()
                )
            }
        }
    }
}