package com.example.playground.app.features.news.data.local

import androidx.paging.PagingSource
import com.example.playground.app.features.news.data.local.models.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalRepository @Inject constructor(
    private val newsDao : NewsDao
) {

    fun getArticles() : PagingSource<Int, ArticleEntity> {
        return newsDao.getAllArticles()
    }

    suspend fun insertArticles(articles : List<ArticleEntity>) {
        withContext(Dispatchers.IO) {
            newsDao.insertArticles(articles)
        }
    }

    suspend fun getArticleCount() : Int {
        return withContext(Dispatchers.IO) {
            newsDao.getArticleCount()
        }
    }

}