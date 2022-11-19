package com.example.jokeapi.presentation.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.jokeapi.domain.model.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean {
        val sameMovie = oldItem.id == newItem.id
        return sameMovie
    }

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean {
        val sameMovie = oldItem.id == newItem.id && oldItem.title == newItem.title
        val sameRating = oldItem.rating == newItem.rating
        val sameImage = oldItem.image == newItem.image
        val samePlaylist = oldItem.playlist == newItem.playlist
        return sameMovie && sameRating && sameImage && samePlaylist
    }
}