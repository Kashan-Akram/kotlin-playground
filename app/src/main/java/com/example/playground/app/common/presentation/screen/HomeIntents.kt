package com.example.playground.app.common.presentation.screen

sealed interface HomeIntents {

    data class FeatureClicked(val feature: String) : HomeIntents

}
