package com.example.playground.app.features.news.core.di

import com.example.playground.app.features.news.core.helper.NewsConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@InstallIn(ViewModelComponent::class)
@Module
object RemoteModule {

    @Provides
    @ViewModelScoped
    @Named("NewsHttpClient")
    fun providesNewsHttpClient(
        @Named("BaseClient") baseClient : OkHttpClient
    ) : OkHttpClient {
        return baseClient.newBuilder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        name = "Authorization",
                        value = NewsConstants.API_KEY
                    ).build()
                chain.proceed(request)
            }.build()
    }

    @Provides
    @ViewModelScoped
    @Named("NewsRetrofit")
    fun providesNewsRetrofit(
        @Named("NewsHttpClient") newsHttpClient : OkHttpClient,
        gsonFactory : GsonConverterFactory
    ) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonFactory)
            .client(newsHttpClient)
            .baseUrl(NewsConstants.BASE_URL)
            .build()
    }

}