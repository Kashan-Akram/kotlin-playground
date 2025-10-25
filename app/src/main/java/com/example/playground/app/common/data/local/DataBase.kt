package com.example.playground.app.common.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.playground.app.features.news.data.local.NewsDao
import com.example.playground.app.features.news.data.local.models.ArticleEntity

@Database(
    entities = [
        ArticleEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}