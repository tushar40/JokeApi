package com.example.jokeapi.presentation.screens.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokeapi.R
import com.example.jokeapi.databinding.ActivityMainBinding
import com.example.jokeapi.domain.model.Movie
import com.example.jokeapi.presentation.screens.adapters.MoviesAdapter
import com.example.jokeapi.presentation.model.UiState
import com.example.jokeapi.presentation.screens.fragments.BottomSheetPlaylist
import com.example.jokeapi.utils.getStringResource
import com.example.jokeapi.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity: AppCompatActivity(), MoviesAdapter.MovieClicked, BottomSheetPlaylist.AddClicked {
    private val movieViewModel: MovieViewModel by viewModels()
    private val pullRequestAdapter by lazy { MoviesAdapter(this) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.movies)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        movieViewModel.getUiStates().observe(this) { uiState ->
            showCurrentUiState(uiState)
        }
        movieViewModel.startFetchingMovies()
    }

    private fun setUpUI() {
        with(binding) {
            rvJokes.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = pullRequestAdapter
            }
        }
    }

    private fun showJokes(questions: List<Movie>) {
        showList()
        pullRequestAdapter.submitList(questions)
    }

    private fun showCurrentUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Content<*> -> {
                if (uiState.data == null) {
                    Toast.makeText(applicationContext, getString(R.string.couldnt_fetch_details), Toast.LENGTH_SHORT).show()
                } else {
                    showJokes(uiState.data as List<Movie>)
                }
            }
            is UiState.Progress -> showLoading()
            is UiState.NetworkError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.no_internet_message)
            )
            is UiState.ServerError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.server_error_message)
            )
            is UiState.ServerDownError -> showErrorView(uiState.clickListener, uiState.msg
                ?: getStringResource(R.string.server_error_message)
            )
        }
    }

    private fun showLoading() {
        with(binding) {
            loadingView.isVisible = true
        }
    }

    private fun showErrorView(clickListener: View.OnClickListener? = null, msg: String? = "") {
        with(binding) {
            loadingView.isVisible = false
        }
    }

    private fun showList() {
        with(binding) {
            loadingView.isVisible = false
        }
    }

    override fun onStarClicked(movie: Movie) {
        BottomSheetPlaylist(movie).show(supportFragmentManager, "")
    }

    override fun onAddPlaylistClicked() {
        // TODO: open Add Playlist Dialog
    }
}