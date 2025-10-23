package com.example.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.playground.app.common.core.helper.navigation.NavigationListener
import com.example.playground.app.common.core.helper.navigation.Navigator
import com.example.playground.app.common.navigation.MainNavigation
import com.example.playground.theme.PlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaygroundTheme {
                val navController = rememberNavController()
                NavigationListener(navController, navigator)
                MainNavigation(navController)
            }
        }
    }
}