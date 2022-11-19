package com.example.jokeapi.presentation.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jokeapi.databinding.ItemMovieBinding
import com.example.jokeapi.domain.model.Movie

class MoviesAdapter(val mListener: MovieClicked): ListAdapter<Movie, MoviesAdapter.ViewHolder>(
    MovieDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        infix fun bind(data: Movie) {
            with(binding) {
                movie = data
                tvRating.text = "Rating: ${data.rating}"
                tvPlaylist.text = "Playlist: ${data.playlist}"

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(data.image)
                    .into(ivMovie)

                btnAddPlaylist.setOnClickListener {
                    mListener.onStarClicked(data)
                }
            }
        }
    }

    interface MovieClicked {
        fun onStarClicked(movie: Movie)
    }
}