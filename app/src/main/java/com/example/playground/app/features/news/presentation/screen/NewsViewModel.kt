package com.example.playground.app.features.news.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playground.app.common.core.usecase.UseCaseHandler
import com.example.playground.app.features.news.domain.models.Article
import com.example.playground.app.features.news.domain.usecase.NewsListUseCase
import com.example.playground.app.features.news.domain.usecase.NewsRequestValues
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsListUseCase: NewsListUseCase,
    private val useCaseHandler: UseCaseHandler
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private var isLoadingMore = false

    init {
        loadNews()
    }

    fun handleIntents(intent: NewsIntents) {
        when (intent) {
            NewsIntents.LoadMore -> {
                loadNews()
            }
        }
    }

    fun loadNews() {
        if (isLoading() || isLoadingMore) {
            Log.d("myLogs", "view model function returned early")
            return
        }
        setLoading()
        isLoadingMore = true
        viewModelScope.launch {
            val state = _uiState.value
            val pageToLoad = state.loadedPagesRange.last + 1
            val newsResult = useCaseHandler.execute(
                useCase = newsListUseCase,
                values = NewsRequestValues(
                    query = "android",
                    articlesPerPage = 10,
                    page = pageToLoad
                ),
                dispatcher = Dispatchers.IO
            )
            newsResult.fold(
                success = { data ->
                    handleSuccessfulLoad(data.articles, pageToLoad)
                },
                failure = { error ->
                    handleError()
                }
            )
            isLoadingMore = false
        }
    }

    private fun handleSuccessfulLoad(newArticles: List<Article>, loadedPage: Int) {
        val state = _uiState.value
        val currentArticles = state.newsList
        val totalCombinedArticles = currentArticles + newArticles
        val newPageRange = (state.loadedPagesRange.first)..(loadedPage)
         _uiState.update { state ->
             state.copy(
                 isLoading = false,
                 newsList = totalCombinedArticles,
                 loadedPagesRange = newPageRange,
                 atEnd = newArticles.isEmpty(),
                 atStart = loadedPage == 1
             )
         }
    }

    private fun handleError() {
        _uiState.update { state ->
            state.copy(
                isLoading = false
            )
        }
    }

    private fun isLoading() : Boolean {
        return _uiState.value.isLoading
    }

    private fun setLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
    }

}