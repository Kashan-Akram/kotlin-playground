package com.example.playground.app.features.news.core.network

import com.example.playground.app.features.news.core.helper.NewsConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NewsAuthInterceptor @Inject constructor(

) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = NewsConstants.API_KEY
        token.takeIf { it.isNotBlank()}?.let {
            request.addHeader(
                name = "Authorization",
                value = it
            )
        }
        return chain.proceed(request.build())
    }
}