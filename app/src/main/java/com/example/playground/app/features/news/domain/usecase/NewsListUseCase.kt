package com.example.playground.app.features.news.domain.usecase

import androidx.paging.PagingData
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.models.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsPagedUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(queries: NewsQueries): Flow<PagingData<Article>> {
        return newsRepository.getNewsPaged(queries)
    }
}

//class NewsListUseCase @Inject constructor(
//    private val newsRepository: NewsRepository
//) : SuspendUseCase<NewsRequestValues, News>() {
//    override suspend fun executeUseCase(requestValues: NewsRequestValues): Resultt<News> {
//        return newsRepository.getNews(
//            NewsQueries(
//                query = requestValues.query,
//                articlesPerPage = requestValues.articlesPerPage,
//                page = requestValues.page
//            )
//        )
//    }
//}
//
//data class NewsRequestValues(
//    val query: String,
//    val articlesPerPage: Int,
//    val page: Int
//) : SuspendUseCase.RequestValues