package com.example.playground.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.playground.app.common.presentation.destination.Home
import com.example.playground.app.common.presentation.destination.homeDestination

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        homeDestination()
    }
}