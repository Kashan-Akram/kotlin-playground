package com.example.playground.app.common.presentation.destination

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.playground.app.common.navigation.routes.AppRoutes
import com.example.playground.app.common.presentation.screen.HomeViewModel

fun NavGraphBuilder.homeDestination(
    navController: NavController
) {
    composable<AppRoutes.Home> {
        val homeViewModel = hiltViewModel<HomeViewModel>()

    }
}