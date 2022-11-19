package com.example.jokeapi.domain.useCase

import com.example.jokeapi.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke() = movieRepository.getMovies()
}