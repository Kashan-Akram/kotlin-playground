package com.example.playground.app.features.news.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.playground.app.features.news.data.local.models.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles() : Flow<List<ArticleEntity>>

    @Query("SELECT NOT EXISTS (SELECT * FROM articles LIMIT 1)")
    suspend fun isNewsDbEmpty() : Boolean

}