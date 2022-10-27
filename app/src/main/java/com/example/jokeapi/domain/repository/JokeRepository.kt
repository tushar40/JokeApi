package com.example.jokeapi.domain.repository

import com.example.jokeapi.domain.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun fetchJoke()
    suspend fun getJokes(): Flow<List<Joke>>
}