package com.example.jokeapi.domain.useCase

import android.util.Log
import com.example.jokeapi.domain.repository.JokeRepository
import com.example.jokeapi.utils.Constants
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timerTask

class StartFetchingJokesUseCase @Inject constructor(private val jokesRepository: JokeRepository) {
    operator fun invoke() {
        Timer().schedule(
            timerTask {
                Log.e("Timer", "running....")
                jokesRepository.fetchJoke()
            },
            0L,
            Constants.FETCH_INTERVAL,
        )
    }
}