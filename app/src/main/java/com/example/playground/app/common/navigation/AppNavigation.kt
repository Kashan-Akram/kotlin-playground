package com.example.playground.app.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.playground.app.common.navigation.routes.AppRoutes
import com.example.playground.app.common.presentation.destination.homeDestination

fun NavGraphBuilder.appNavigation(
    navController: NavController
) {

    navigation<AppRoutes>(
        startDestination = AppRoutes.Home
    ) {
        homeDestination(navController)
    }



}