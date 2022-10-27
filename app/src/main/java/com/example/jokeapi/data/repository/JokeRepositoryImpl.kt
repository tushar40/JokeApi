package com.example.jokeapi.data.repository

import android.util.Log
import com.example.jokeapi.data.database.roomDatabase.dao.JokesDao
import com.example.jokeapi.data.model.JokeDTO
import com.example.jokeapi.data.model.toJoke
import com.example.jokeapi.data.network.APIInterface
import com.example.jokeapi.domain.repository.JokeRepository
import com.example.jokeapi.domain.model.ResultWrapper
import com.example.jokeapi.utils.makeApiCall
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val mApiService: APIInterface,
    private val jokesDao: JokesDao): JokeRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun fetchJoke() {
        coroutineScope.launch {
            makeApiCall {
                mApiService.getJoke()
            }.collect {
                if (it is ResultWrapper.OnSuccess) {
                    jokesDao.insertAndDelete(JokeDTO(it.data))
                }
            }
        }
    }

    override suspend fun getJokes() = jokesDao.getAll().map {
        Log.e("Timer", it.size.toString())
        it.map { it.toJoke() }
    }
}