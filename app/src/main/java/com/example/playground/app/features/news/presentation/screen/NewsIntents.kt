package com.example.playground.app.features.news.presentation.screen

sealed interface NewsIntents {

    data object LoadMore : NewsIntents

}
