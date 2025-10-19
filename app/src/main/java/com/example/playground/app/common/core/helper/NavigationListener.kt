package com.example.playground.app.common.core.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController

@Composable
fun NavigatorListener(
    navController: NavController,
    navigator: Navigator
) {
    LaunchedEffect(Unit) {
        navigator.route.collect { route ->
            navController.navigate(route)
        }
    }
}