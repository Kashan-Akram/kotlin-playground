package com.example.playground.app.common.core.helper.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.navOptions

@Composable
fun NavigationListener(
    navController: NavController,
    navigator: Navigator
) {
    LaunchedEffect(Unit) {
        navigator.navEvents.collect { evt ->
            val navOpts = evt.options?.let { navOptions(it) }
            if (navOpts != null) {
                navController.navigate(evt.route, navOpts)
            } else {
                navController.navigate(evt.route)
            }
        }
    }
}