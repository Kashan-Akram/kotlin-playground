package com.example.playground.app.features.news.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles"
)
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("article_ID") val articleID : Int,
    @ColumnInfo("title") val title : String,
    @ColumnInfo("description") val description : String,
    @ColumnInfo("content") val content : String,
    @ColumnInfo("url_to_image") val urlToImage : String,
)
