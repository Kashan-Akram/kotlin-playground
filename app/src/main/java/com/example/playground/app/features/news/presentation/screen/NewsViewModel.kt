package com.example.playground.app.features.news.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.playground.app.common.core.usecase.UseCaseHandler
import com.example.playground.app.features.news.domain.usecase.GetNewsPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsPagedUseCase: GetNewsPagedUseCase,
    private val useCaseHandler: UseCaseHandler
) : ViewModel() {

    val articlesPaging = newsPagedUseCase.getNewsViaRemoteMediator().cachedIn(viewModelScope)

    /*val articlesPagingFromAPI : Flow<PagingData<Article>> = newsPagedUseCase.getNewsPagedFromAPI(NewsQueries("android")).cachedIn(viewModelScope)

    val articlesPagingFromDB : Flow<PagingData<Article>> = newsPagedUseCase.getNewsPagedFromDB().cachedIn(viewModelScope)

    fun insertArticlesToDB() {
        viewModelScope.launch {
            articlesPagingFromAPI.collectLatest { pagingData ->
                val articles = mutableListOf<Article>()

                pagingData.map { article ->
                    articles.add(article)
                    if (articles.size >= 20) { // batch size (page)
                        newsPagedUseCase.insertArticlesToDB(articles)
                        articles.clear()
                    }
                }
            }
        }
    }*/

}