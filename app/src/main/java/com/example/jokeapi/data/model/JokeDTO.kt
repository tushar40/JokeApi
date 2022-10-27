package com.example.jokeapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jokeapi.domain.model.Joke

@Entity
data class JokeDTO(
    @PrimaryKey
    val description: String
)

fun JokeDTO.toJoke(): Joke {
    return Joke(description)
}
