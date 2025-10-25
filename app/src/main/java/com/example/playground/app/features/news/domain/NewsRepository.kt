package com.example.playground.app.features.news.domain

import com.example.playground.app.common.core.models.Resultt
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.models.News

interface NewsRepository {
    suspend fun getNews(queries: NewsQueries) : Resultt<News>
}