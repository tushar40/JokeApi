package com.example.jokeapi.domain.useCase

import com.example.jokeapi.domain.model.Movie
import com.example.jokeapi.domain.repository.MovieRepository
import javax.inject.Inject

class UpdatePlaylistUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movie: Movie, playlist: String) = movieRepository.updatePlaylist(movie, playlist)
}