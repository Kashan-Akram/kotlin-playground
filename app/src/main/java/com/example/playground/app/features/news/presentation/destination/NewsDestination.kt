package com.example.playground.app.features.news.presentation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.app.features.news.presentation.screen.NewsScreen
import kotlinx.serialization.Serializable

@Serializable data object News

fun NavGraphBuilder.newsDestination() {
    composable<News> {
        NewsScreen()
    }
}