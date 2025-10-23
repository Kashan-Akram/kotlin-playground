package com.example.playground.app.common.presentation.destination

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.app.common.presentation.screen.HomeScreen
import com.example.playground.app.common.presentation.screen.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable data object Home

fun NavGraphBuilder.homeDestination() {
    composable<Home> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(uiState, viewModel::processIntent)
    }
}