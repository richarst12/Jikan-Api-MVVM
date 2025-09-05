package com.example.jikan_api_mvvm.di

import android.content.Context
import androidx.room.Room
import com.example.jikan_api_mvvm.data.local.AnimeDao
import com.example.jikan_api_mvvm.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "anime.db").build()

    @Provides
    fun provideAnimeDao(db: AppDatabase): AnimeDao = db.animeDao()
}
