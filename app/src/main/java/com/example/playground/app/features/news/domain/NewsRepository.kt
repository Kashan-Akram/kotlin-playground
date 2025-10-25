package com.example.playground.app.features.news.domain

import androidx.paging.PagingData
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

//    suspend fun getNews(queries: NewsQueries) : Resultt<News>

    fun getNewsPaged(queries: NewsQueries) : Flow<PagingData<Article>>

}