package com.example.jokeapi.data.network

import com.example.jokeapi.data.model.JokeDTO
import com.example.jokeapi.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET(Constants.ENDPOINT)
    suspend fun getJoke(): Response<String>
}