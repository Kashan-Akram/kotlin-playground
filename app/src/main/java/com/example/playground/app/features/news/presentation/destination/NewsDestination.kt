package com.example.playground.app.features.news.presentation.destination

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.app.features.news.presentation.screen.NewsScreen
import com.example.playground.app.features.news.presentation.screen.NewsViewModel
import kotlinx.serialization.Serializable

@Serializable data object News

fun NavGraphBuilder.newsDestination() {
    composable<News> {
        val viewModel = hiltViewModel<NewsViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        NewsScreen(uiState, viewModel::handleIntents)
    }
}