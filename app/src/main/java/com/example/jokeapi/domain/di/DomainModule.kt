package com.example.jokeapi.domain.di

import com.example.jokeapi.data.database.roomDatabase.dao.JokesDao
import com.example.jokeapi.data.network.APIInterface
import com.example.jokeapi.data.repository.JokeRepositoryImpl
import com.example.jokeapi.domain.repository.JokeRepository
import com.example.jokeapi.domain.useCase.GetJokesUseCase
import com.example.jokeapi.domain.useCase.StartFetchingJokesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideJokeRepository(apiInterface: APIInterface, jokesDao: JokesDao): JokeRepository {
        return JokeRepositoryImpl(apiInterface, jokesDao)
    }

    @Provides
    fun provideGetJokesUseCase(jokeRepository: JokeRepository): GetJokesUseCase {
        return GetJokesUseCase(jokeRepository)
    }

    @Provides
    fun provideStartFetchingJokesUseCase(jokeRepository: JokeRepository): StartFetchingJokesUseCase {
        return StartFetchingJokesUseCase(jokeRepository)
    }
}