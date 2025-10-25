package com.example.playground.app.features.news.data

import com.example.playground.app.common.core.models.Resultt
import com.example.playground.app.features.news.data.mappers.toDomain
import com.example.playground.app.features.news.data.remote.NewsRemoteRepository
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.models.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository
) : NewsRepository {
    override suspend fun getNews(queries: NewsQueries): Resultt<News> {
        return when (val newsResponse = newsRemoteRepository.getNews(queries)) {
            is Resultt.Success -> Resultt.Success(newsResponse.data.toDomain())
            is Resultt.Failure -> newsResponse
        }
    }
}