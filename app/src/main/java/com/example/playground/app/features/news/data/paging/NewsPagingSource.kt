package com.example.playground.app.features.news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.playground.app.common.core.models.Resultt
import com.example.playground.app.features.news.data.remote.NewsRemoteRepository
import com.example.playground.app.features.news.data.remote.models.ArticleResponse
import com.example.playground.app.features.news.data.remote.models.NewsQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsPagingSource(
    private val newsRemoteRepository: NewsRemoteRepository,
    private val queries: NewsQueries
) : PagingSource<Int, ArticleResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleResponse> {
        return withContext (Dispatchers.IO) {
            try {
                val page = params.key ?: 1
                val pageSize = params.loadSize

                val response = newsRemoteRepository.getNews(
                    NewsQueries(
                        query = queries.query,
                        page = page,
                        articlesPerPage = pageSize
                    )
                )

                when (response) {
                    is Resultt.Success -> {
                        val articles = response.data.articles.orEmpty().filterNotNull()
                        LoadResult.Page(
                            data = articles,
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (response.data.articles?.isEmpty() == true) null else page + 1
                        )
                    }

                    is Resultt.Failure -> {
                        LoadResult.Error(Throwable("e"))
                    }
                }

            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
