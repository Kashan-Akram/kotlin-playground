package com.example.playground.app.features.news.core.di

import com.example.playground.app.common.data.local.DataBase
import com.example.playground.app.features.news.data.local.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object LocalModule {

    @ViewModelScoped
    @Provides
    fun provideNewsDao(db: DataBase): NewsDao {
        return db.newsDao()
    }

}