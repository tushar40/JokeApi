package com.example.jokeapi.data.network

import com.example.jokeapi.data.model.MovieResponseDTO
import com.example.jokeapi.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET(Constants.POPULAR_MOVIES)
    suspend fun getPopularMovies(): Response<MovieResponseDTO>
}