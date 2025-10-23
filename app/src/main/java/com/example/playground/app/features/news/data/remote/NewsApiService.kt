package com.example.playground.app.features.news.data.remote

import com.example.playground.app.features.news.core.models.remote.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") query : String? = null,
        @Query("from") oldestDate : String? = null,
        @Query("to") earliestDate : String? = null,
        @Query("language") language : String = "en",
        @Query("sortBy") sortBy : String? = null,
        @Query("pageSize") articlesPerPage : Int? = null,
        @Query("page") page : Int? = null,
    ) : NewsResponse

}