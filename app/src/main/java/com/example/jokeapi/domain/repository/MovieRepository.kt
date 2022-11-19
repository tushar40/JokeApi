package com.example.jokeapi.domain.repository

import com.example.jokeapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMovies()
    suspend fun getMovies(): Flow<List<Movie>>
    suspend fun updatePlaylist(movie: Movie, playlist: String)
}