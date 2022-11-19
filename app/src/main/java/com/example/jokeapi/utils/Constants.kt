package com.example.jokeapi.utils

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "38a73d59546aa378980a88b645f487fc"
    private const val lang = "en"
    const val POPULAR_MOVIES = "movie/popular?api_key=$API_KEY&language=$lang&page=1"
}