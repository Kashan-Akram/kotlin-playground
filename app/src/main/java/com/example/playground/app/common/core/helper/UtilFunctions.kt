package com.example.playground.app.common.core.helper

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

object UtilFunctions {

    fun randomColor(): Color {
        val red = Random.Default.nextInt(256)
        val green = Random.Default.nextInt(256)
        val blue = Random.Default.nextInt(256)
        return Color(red, green, blue)
    }

}