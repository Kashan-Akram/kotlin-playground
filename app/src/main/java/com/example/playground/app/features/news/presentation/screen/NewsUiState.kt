package com.example.playground.app.features.news.presentation.screen

import com.example.playground.app.features.news.domain.models.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val newsList: List<Article> = emptyList(),
    val error: String = "",
    val loadedPagesRange: IntRange = 1..1,
    val atEnd: Boolean = false,
    val atStart: Boolean = true
)

enum class LoadDirection {
    APPEND, PREPEND
}