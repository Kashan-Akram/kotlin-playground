package com.example.playground.app.features.news.data.remote

import com.example.playground.app.common.core.models.Resultt
import com.example.playground.app.common.core.network.networkBoundOperation
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.data.remote.models.NewsResponse
import javax.inject.Inject

class NewsRemoteRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    suspend fun getNews(queries : NewsQueries) : Resultt<NewsResponse> {
        return networkBoundOperation {
            newsApiService.getAllNews(
                query = queries.query,
                articlesPerPage = queries.articlesPerPage,
                page = queries.page
            )
        }
    }
}