package com.example.playground.app.common.core.di

import com.example.playground.app.common.core.usecase.UseCaseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesUseCaseHandler() : UseCaseHandler {
        return UseCaseHandler.getInstance()
    }

}