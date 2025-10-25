package com.example.playground.app.features.news.data.remote.models

import androidx.annotation.Keep
import kotlinx.serialization.SerialName

@Keep
data class NewsResponse(
    @SerialName("status") val status : String? = null,
    @SerialName("totalResults") val newsCount : Int? = null,
    @SerialName("articles") val articles : List<ArticleResponse?>? = null,
)

@Keep
data class ArticleResponse(
    @SerialName("source") val source: Source? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("urlToImage") val urlToImage: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("content") val content: String? = null
)

@Keep
data class Source(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null
)