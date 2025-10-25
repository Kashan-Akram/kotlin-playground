package com.example.playground.app.features.news.data.RemoteMediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.playground.app.features.news.data.local.NewsLocalRepository
import com.example.playground.app.features.news.data.local.models.ArticleEntity
import com.example.playground.app.features.news.data.mappers.toData
import com.example.playground.app.features.news.data.remote.NewsRemoteRepository
import com.example.playground.app.features.news.data.remote.models.NewsQueries

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val newsLocalRepository: NewsLocalRepository,
    private val newsRemoteRepository: NewsRemoteRepository
) : RemoteMediator<Int, ArticleEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val itemCount = newsLocalRepository.getArticleCount()
                    val nextPage = (itemCount / 10) + 1
                    if (itemCount == 0) 1 else nextPage
                }
            }

            val response = newsRemoteRepository.getNews(NewsQueries(query = "android", page = page))

            response.fold(
                success = { data ->
                    newsLocalRepository.insertArticles(data.articles.orEmpty().filterNotNull().map { it.toData()})
                    return MediatorResult.Success(endOfPaginationReached = data.articles.orEmpty().filterNotNull().isEmpty())
                },
                failure = { }
            )

            return MediatorResult.Error(Throwable("e"))

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

}