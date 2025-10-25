package com.example.playground.app.features.news.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.playground.app.features.news.data.RemoteMediator.NewsRemoteMediator
import com.example.playground.app.features.news.data.local.NewsLocalRepository
import com.example.playground.app.features.news.data.mappers.toData
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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class NewsRepositoryImpl @Inject constructor(
    private val newsLocalRepository: NewsLocalRepository,
    private val newsRemoteRepository: NewsRemoteRepository
) : NewsRepository {

    override fun getNewsPagedFromDB(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                newsLocalRepository.getArticles()
            }
        ).flow.map { pagingData ->
            pagingData.map { articleEntity ->
                articleEntity.toDomain()
            }
        }.flowOn(Dispatchers.IO)
    }

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
            pagingData.map { articleResponse ->
                articleResponse.toDomain()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertArticles(articles : List<Article>) {
        withContext(Dispatchers.IO) {
            newsLocalRepository.insertArticles(articles.map { it.toData() })
        }
    }

    override fun getNewsPagedViaRemoteMediator(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                newsLocalRepository.getArticles()
            },
            remoteMediator = NewsRemoteMediator(
                newsLocalRepository = newsLocalRepository,
                newsRemoteRepository = newsRemoteRepository
            )
        ).flow.map { pagingData ->
            pagingData.map { articleEntity ->
                articleEntity.toDomain()
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