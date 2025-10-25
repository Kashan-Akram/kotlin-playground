package com.example.playground.app.features.news.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.playground.app.common.core.usecase.UseCaseHandler
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.models.Article
import com.example.playground.app.features.news.domain.usecase.GetNewsPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsPagedUseCase: GetNewsPagedUseCase,
    private val useCaseHandler: UseCaseHandler
) : ViewModel() {

    val articlesFlow : Flow<PagingData<Article>> = newsPagedUseCase(NewsQueries("android")).cachedIn(viewModelScope)

}