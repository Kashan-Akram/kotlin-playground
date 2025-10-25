package com.example.playground.app.features.news.presentation.screen

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

    private val maxArticlesInMemory = 50
    private val maxArticlesInPage = 10
    private val maxPagesInMemory = maxArticlesInMemory / maxArticlesInPage

    init {
        loadNews(LoadDirection.APPEND)
    }

    private fun loadNews(loadDirection: LoadDirection) {
        setLoading()
        viewModelScope.launch {
            val state = _uiState.value
            val pageToLoad = when (loadDirection) {
                LoadDirection.APPEND -> {
                    (state.loadedPagesRange.last + 1)
                }
                LoadDirection.PREPEND -> {
                    (state.loadedPagesRange.first - 1).coerceAtLeast(1)
                }
            }
            val newsResult = useCaseHandler.execute(
                useCase = newsListUseCase,
                values = NewsRequestValues(
                    query = "",
                    articlesPerPage = maxArticlesInPage,
                    page = pageToLoad
                ),
                dispatcher = Dispatchers.IO
            )
            newsResult.fold(
                success = { data ->
                    handleSuccessfulLoad(data.articles, pageToLoad, loadDirection)
                },
                failure = { error ->
                    handleError()
                }
            )
        }
    }

    private fun handleSuccessfulLoad(newArticles: List<Article>, loadedPage: Int, loadDirection: LoadDirection) {
        val state = _uiState.value
        val currentArticles = state.newsList
        val totalCombinedArticles = when (loadDirection) {
            LoadDirection.APPEND -> {
                currentArticles + newArticles
            }
            LoadDirection.PREPEND -> {
                newArticles + currentArticles
            }
        }
        val newPageRange = when (loadDirection) {
            LoadDirection.APPEND -> {
                (state.loadedPagesRange.first)..(loadedPage)
            }
            LoadDirection.PREPEND -> {
                (loadedPage)..(state.loadedPagesRange.last)
            }
        }
        var finalArticlesAndPageRange : Pair<List<Article>, IntRange>
        val numberOfPagesToDrop = newPageRange.count() - maxPagesInMemory
        val numberOfArticlesToKeep = totalCombinedArticles.size - numberOfPagesToDrop * maxArticlesInPage
        when (loadDirection) {
            LoadDirection.APPEND -> {
                val articlesToTake = totalCombinedArticles.takeLast(numberOfArticlesToKeep)
                val pageRangeToTake = (newPageRange.first + numberOfPagesToDrop)..(newPageRange.last)
                finalArticlesAndPageRange = Pair(articlesToTake, pageRangeToTake)
            }
            LoadDirection.PREPEND -> {
                val articlesToTake = totalCombinedArticles.take(numberOfArticlesToKeep)
                val pageRangeToTake = (newPageRange.first)..(newPageRange.last - numberOfPagesToDrop)
                finalArticlesAndPageRange = Pair(articlesToTake, pageRangeToTake)
            }
        }
         _uiState.update { state ->
             state.copy(
                 isLoading = false,
                 error = "",
                 newsList = finalArticlesAndPageRange.first,
                 loadedPagesRange = finalArticlesAndPageRange.second,
                 atEnd = newArticles.isEmpty() && loadDirection == LoadDirection.APPEND,
                 atStart = loadedPage == 1 && loadDirection == LoadDirection.PREPEND
             )
         }
    }

    private fun handleError() {
        _uiState.update { state ->
            state.copy(
                error = "Error Occurred"
            )
        }
    }

    private fun setLoading() {
        _uiState.update { state ->
            state.copy(
                isLoading = true
            )
        }
    }

}