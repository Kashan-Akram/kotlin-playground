package com.example.playground.app.features.news.data.mappers

import com.example.playground.app.features.news.data.local.models.ArticleEntity
import com.example.playground.app.features.news.data.remote.models.ArticleResponse
import com.example.playground.app.features.news.domain.models.Article

//fun NewsResponse.toDomain() : News {
//    return News(
//        count = this.newsCount ?: 0,
//        articles = this.articles?.mapNotNull { article ->
//            Article(
//                title = article?.title.orEmpty(),
//                description = article?.description.orEmpty(),
//                content = article?.content.orEmpty(),
//                urlToImage = article?.urlToImage.orEmpty()
//            )
//        }.orEmpty()
//    )
//}

fun ArticleResponse.toDomain() : Article {
    return Article(
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        content = this.content.orEmpty(),
        urlToImage = this.urlToImage.orEmpty()
    )
}

fun ArticleEntity.toDomain() : Article {
    return Article(
        title = this.title,
        description = this.description,
        content = this.content,
        urlToImage = this.urlToImage
    )
}

fun Article.toData() : ArticleEntity {
    return ArticleEntity(
        title = this.title,
        description = this.description,
        content = this.content,
        urlToImage = this.urlToImage
    )
}

fun ArticleResponse.toData() : ArticleEntity {
    return ArticleEntity(
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        content = this.content.orEmpty(),
        urlToImage = this.urlToImage.orEmpty()
    )
}