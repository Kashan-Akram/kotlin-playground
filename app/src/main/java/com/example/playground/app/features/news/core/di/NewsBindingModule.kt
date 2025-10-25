package com.example.playground.app.features.news.core.di

import com.example.playground.app.features.news.data.NewsRepositoryImpl
import com.example.playground.app.features.news.domain.NewsRepository
import com.example.playground.app.features.news.domain.usecase.GetNewsPagedUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class NewsBindingModule {

    @ViewModelScoped
    @Binds
    abstract fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ) : NewsRepository

}

@InstallIn(ViewModelComponent::class)
@Module
object NewsModule {

    @ViewModelScoped
    @Provides
    fun provideNewsPagedUseCase(
        newsRepository: NewsRepository
    ) : GetNewsPagedUseCase {
        return GetNewsPagedUseCase(newsRepository)
    }

}