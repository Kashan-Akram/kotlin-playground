package com.example.playground.app.features.news.presentation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable data object News

fun NavGraphBuilder.newsDestination() {
    composable<News> {

    }
}