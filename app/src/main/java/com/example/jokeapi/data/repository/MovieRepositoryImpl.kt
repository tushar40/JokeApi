package com.example.jokeapi.data.repository

import com.example.jokeapi.data.database.roomDatabase.dao.MoviesDao
import com.example.jokeapi.data.model.toDomain
import com.example.jokeapi.data.network.APIInterface
import com.example.jokeapi.domain.model.Movie
import com.example.jokeapi.domain.repository.MovieRepository
import com.example.jokeapi.domain.model.ResultWrapper
import com.example.jokeapi.utils.makeApiCall
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mApiService: APIInterface,
    private val moviesDao: MoviesDao): MovieRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun fetchMovies() {
        coroutineScope.launch {
            makeApiCall {
                mApiService.getPopularMovies()
            }.collect {
                if (it is ResultWrapper.OnSuccess) {
                    moviesDao.insertAll(*it.data.results.toTypedArray())
                }
            }
        }
    }

    override suspend fun getMovies() = moviesDao.getAll().map {
        it.map { it.toDomain() }
    }

    override suspend fun updatePlaylist(movie: Movie, playlist: String) {
        moviesDao.updatePlaylist(movie.id, playlist)
    }
}