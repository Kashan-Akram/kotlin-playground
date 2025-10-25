package com.example.playground.app.features.news.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playground.app.features.news.data.local.models.ArticleEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles : List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun getAllArticles() : PagingSource<Int, ArticleEntity>

    @Query("SELECT NOT EXISTS (SELECT * FROM articles LIMIT 1)")
    suspend fun isNewsDbEmpty() : Boolean

    @Query("SELECT COUNT(*) FROM articles")
    suspend fun getArticleCount() : Int

}