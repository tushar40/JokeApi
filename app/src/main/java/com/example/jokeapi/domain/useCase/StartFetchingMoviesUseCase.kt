package com.example.jokeapi.domain.useCase

import com.example.jokeapi.domain.repository.MovieRepository
import javax.inject.Inject

class StartFetchingMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke() {
        movieRepository.fetchMovies()
    }
}