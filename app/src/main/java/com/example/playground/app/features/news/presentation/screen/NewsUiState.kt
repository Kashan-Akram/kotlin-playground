package com.example.playground.app.features.news.presentation.screen

import androidx.paging.PagingData
import com.example.playground.app.features.news.domain.models.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val newsList: PagingData<Article> = PagingData.empty(),
    val loadedPagesRange: IntRange = 1..1,
    val atEnd: Boolean = false,
    val atStart: Boolean = true
)