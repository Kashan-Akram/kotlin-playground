package com.example.playground.app.features.news.presentation.screen

sealed interface NewsIntents {

    data class ScrollPositionChanged(
        val firstVisibleIndex: Int,
        val lastVisibleIndex: Int
    ) : NewsIntents

}
