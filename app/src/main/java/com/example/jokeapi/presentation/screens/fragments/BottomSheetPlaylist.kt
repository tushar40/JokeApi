package com.example.jokeapi.presentation.screens.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.jokeapi.databinding.BottomSheetPlaylistBinding
import com.example.jokeapi.domain.model.Movie
import com.example.jokeapi.presentation.screens.adapters.PlaylistAdapter
import com.example.jokeapi.presentation.viewmodel.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheetPlaylist(val movie: Movie) : BottomSheetDialogFragment(), PlaylistAdapter.PlaylistClicked {
    private val adapter by lazy { PlaylistAdapter(this, movie) }
    private val viewmodel: MovieViewModel by activityViewModels()
    private var mListener: AddClicked? = null
    private lateinit var binding: BottomSheetPlaylistBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = context as AddClicked
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetPlaylistBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener {
            dismissAllowingStateLoss()
            mListener?.onAddPlaylistClicked()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPlaylist.adapter = adapter
    }

    override fun onPlaylistClicked(movie: Movie, playlist: String) {
        viewmodel.updatePlaylist(movie, playlist)
    }

    interface AddClicked {
        fun onAddPlaylistClicked()
    }
}
