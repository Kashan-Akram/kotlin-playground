package com.example.playground.app.features.news.data.remote.models

data class NewsQueries(
    val query : String? = null,
    val articlesPerPage : Int? = null,
    val page : Int? = null
)
