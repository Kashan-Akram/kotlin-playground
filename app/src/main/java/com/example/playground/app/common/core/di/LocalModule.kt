package com.example.playground.app.common.core.di

import android.content.Context
import androidx.room.Room
import com.example.playground.app.common.data.local.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration(false)
        .build()
    }

}