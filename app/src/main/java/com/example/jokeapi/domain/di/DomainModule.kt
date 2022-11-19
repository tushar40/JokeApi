package com.example.jokeapi.domain.di

import com.example.jokeapi.data.database.roomDatabase.dao.MoviesDao
import com.example.jokeapi.data.network.APIInterface
import com.example.jokeapi.data.repository.MovieRepositoryImpl
import com.example.jokeapi.domain.repository.MovieRepository
import com.example.jokeapi.domain.useCase.GetMoviesUseCase
import com.example.jokeapi.domain.useCase.StartFetchingMoviesUseCase
import com.example.jokeapi.domain.useCase.UpdatePlaylistUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideMovieRepository(apiInterface: APIInterface, jokesDao: MoviesDao): MovieRepository {
        return MovieRepositoryImpl(apiInterface, jokesDao)
    }

    @Provides
    fun provideGetJokesUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideStartFetchingMoviesUseCase(movieRepository: MovieRepository): StartFetchingMoviesUseCase {
        return StartFetchingMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdatePlaylistUseCase(movieRepository: MovieRepository): UpdatePlaylistUseCase {
        return UpdatePlaylistUseCase(movieRepository)
    }
}