package com.example.playground.app.features.news.core.di

import com.example.playground.app.features.news.data.NewsRepositoryImpl
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.usecase.NewsListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class NewsModule {

    @Binds
    @Provides
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : NewsRepository

    @ViewModelScoped
    @Provides
    fun provideNewsListUseCase(
        newsRepository: NewsRepository
    ) : NewsListUseCase {
        return NewsListUseCase(newsRepository)
    }

}