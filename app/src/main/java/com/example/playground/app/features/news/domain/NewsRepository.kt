package com.example.playground.app.features.news.domain

import androidx.paging.PagingData
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

//    suspend fun getNews(queries: NewsQueries) : Resultt<News>

    fun getNewsPagedViaRemoteMediator() : Flow<PagingData<Article>>

    fun getNewsPaged(queries: NewsQueries) : Flow<PagingData<Article>>

    fun getNewsPagedFromDB() : Flow<PagingData<Article>>

    suspend fun insertArticles(articles : List<Article>)

}