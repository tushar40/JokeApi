package com.example.jokeapi.presentation.screens.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.jokeapi.domain.model.Movie

class PlaylistDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }
}