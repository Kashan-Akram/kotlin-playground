package com.example.playground.app.features.news.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.playground.app.features.news.data.mappers.toDomain
import com.example.playground.app.features.news.data.paging.NewsPagingSource
import com.example.playground.app.features.news.data.remote.NewsRemoteRepository
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteRepository: NewsRemoteRepository
) : NewsRepository {

    override fun getNewsPaged(queries: NewsQueries): Flow<PagingData<Article>> {
        return Pager(
            config =  PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsRemoteRepository, queries)
            }
        ).flow.map { pagingData ->
            // Map from ArticleResponse (DTO) to Article (Domain)
            pagingData.map { articleResponse ->
                articleResponse.toDomain()
            }
        }.flowOn(Dispatchers.IO)
    }

//    override suspend fun getNews(queries: NewsQueries): Resultt<News> {
//        return when (val newsResponse = newsRemoteRepository.getNews(queries)) {
//            is Resultt.Success -> Resultt.Success(newsResponse.data.toDomain())
//            is Resultt.Failure -> newsResponse
//        }
//    }
}