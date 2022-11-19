package com.example.jokeapi.data.di

import com.example.jokeapi.data.database.roomDatabase.AppDatabase
import com.example.jokeapi.data.database.roomDatabase.dao.MoviesDao
import com.example.jokeapi.data.network.APIInterface
import com.example.jokeapi.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    fun provideAPIInterface(): APIInterface {
        return ApiClient.getApi()
    }

    @Provides
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getInstance()
    }

    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao {
        return appDatabase.moviesDao()
    }
}