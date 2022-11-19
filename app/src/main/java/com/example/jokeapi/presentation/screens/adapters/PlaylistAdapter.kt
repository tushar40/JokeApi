package com.example.jokeapi.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jokeapi.databinding.ItemMovieBinding
import com.example.jokeapi.databinding.ItemPlaylistBinding
import com.example.jokeapi.domain.model.Movie

class PlaylistAdapter(val mListener: PlaylistClicked, val movie: Movie): ListAdapter<String, PlaylistAdapter.ViewHolder>(
    PlaylistDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaylistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        infix fun bind(data: String) {
            with(binding) {
                tvPlaylist.text = data
            }
            itemView.setOnClickListener {
                mListener.onPlaylistClicked(movie, data)
            }
        }
    }

    interface PlaylistClicked {
        fun onPlaylistClicked(movie: Movie, playlist: String)
    }
}