package com.example.playground.app.features.news.domain.usecase

import com.example.playground.app.common.core.models.Resultt
import com.example.playground.app.common.core.usecase.SuspendUseCase
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.models.News
import javax.inject.Inject

class NewsListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : SuspendUseCase<NewsRequestValues, News>() {
    override suspend fun executeUseCase(requestValues: NewsRequestValues): Resultt<News> {
        return newsRepository.getNews(
            NewsQueries(
                query = requestValues.query,
                articlesPerPage = requestValues.articlesPerPage,
                page = requestValues.page
            )
        )
    }
}

data class NewsRequestValues(
    val query: String,
    val articlesPerPage: Int,
    val page: Int
) : SuspendUseCase.RequestValues