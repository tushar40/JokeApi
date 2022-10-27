package com.example.jokeapi.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapi.databinding.ItemJokeBinding
import com.example.jokeapi.domain.model.Joke

class JokesAdapter: ListAdapter<Joke, JokesAdapter.ViewHolder>(
    JokeDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {
        infix fun bind(data: Joke) {
            binding.joke = data
        }
    }
}