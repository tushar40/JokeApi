package com.example.jokeapi.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val image: String,
    val rating: Double,
    var playlist: String?
    )
