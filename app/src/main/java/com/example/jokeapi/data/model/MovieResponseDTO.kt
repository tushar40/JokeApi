package com.example.jokeapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jokeapi.domain.model.Movie

data class MovieResponseDTO(
    val page: Int,
    val results: List<MovieDTO>
)

@Entity
data class MovieDTO(
    @PrimaryKey
    val id: Long,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    var playlist: String? = null
    )

fun MovieDTO.toDomain(): Movie {
    val basePath = "https://image.tmdb.org/t/p/w500"
    return Movie(id, title, "$basePath/$backdrop_path", vote_average, playlist)
}
