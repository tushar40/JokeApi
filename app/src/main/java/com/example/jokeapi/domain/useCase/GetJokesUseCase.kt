package com.example.jokeapi.domain.useCase

import com.example.jokeapi.domain.repository.JokeRepository
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(private val jokesRepository: JokeRepository) {
    suspend operator fun invoke() = jokesRepository.getJokes()
}