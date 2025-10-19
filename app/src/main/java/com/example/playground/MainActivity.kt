package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.playground.app.common.core.helper.Navigator
import com.example.playground.app.common.core.helper.NavigatorListener
import com.example.playground.app.common.navigation.appNavigation
import com.example.playground.app.common.navigation.routes.AppRoutes
import com.example.playground.theme.PlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject private lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundTheme {
                val navController = rememberNavController()
                NavigatorListener(navController, navigator)
                NavHost(
                    navController = navController,
                    startDestination = AppRoutes.Home
                ) {
                    appNavigation(navController)
                }
            }
        }
    }
}