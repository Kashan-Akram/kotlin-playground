package com.example.playground.app.common.core.di

import android.content.Context
import androidx.room.Room
import com.example.playground.app.common.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): DataBase {
        return Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration(false)
        .build()
    }

}