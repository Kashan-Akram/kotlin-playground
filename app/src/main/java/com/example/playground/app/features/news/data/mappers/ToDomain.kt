package com.example.playground.app.features.news.data.mappers

import com.example.playground.app.features.news.data.remote.models.NewsResponse
import com.example.playground.app.features.news.domain.models.Article
import com.example.playground.app.features.news.domain.models.News

fun NewsResponse.toDomain() : News {
    return News(
        count = this.newsCount ?: 0,
        articles = this.articles?.mapNotNull { article ->
            Article(
                title = article?.title.orEmpty(),
                description = article?.description.orEmpty(),
                content = article?.content.orEmpty(),
                urlToImage = article?.urlToImage.orEmpty()
            )
        }.orEmpty()
    )
}