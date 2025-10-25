package com.example.playground.app.features.news.domain.models

import androidx.annotation.Keep

@Keep
data class News(
    val count : Int = 0,
    val articles : List<Article> = emptyList()
)

@Keep
data class Article(
    val title: String = "",
    val description: String = "",
    val content: String = "",
    val urlToImage: String = ""
)
