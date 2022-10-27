package com.example.jokeapi.presentation.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.jokeapi.domain.model.Joke

class JokeDiffCallback: DiffUtil.ItemCallback<Joke>() {
    override fun areItemsTheSame(
        oldItem: Joke,
        newItem: Joke
    ): Boolean {
        val sameJoke = oldItem.description == newItem.description
        return sameJoke
    }

    override fun areContentsTheSame(
        oldItem: Joke,
        newItem: Joke
    ): Boolean {
        val sameJoke = oldItem.description == newItem.description
        return sameJoke
    }
}