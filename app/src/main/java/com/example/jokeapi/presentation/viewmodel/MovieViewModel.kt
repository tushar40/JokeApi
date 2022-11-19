package com.example.jokeapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapi.domain.model.Movie
import com.example.jokeapi.domain.useCase.GetMoviesUseCase
import com.example.jokeapi.domain.useCase.StartFetchingMoviesUseCase
import com.example.jokeapi.domain.useCase.UpdatePlaylistUseCase
import com.example.jokeapi.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val startFetchingMoviesUseCase: StartFetchingMoviesUseCase,
    private val updatePlaylistUseCase: UpdatePlaylistUseCase,
): ViewModel() {
    private val currentUiState: MutableLiveData<UiState> = MutableLiveData()
    fun getUiStates(): LiveData<UiState> = currentUiState

    fun startFetchingMovies() {
        currentUiState.value = UiState.Progress
        startFetchingMoviesUseCase()
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMoviesUseCase().collect { result ->
                currentUiState.postValue(UiState.Content(result))
            }
        }
    }

    fun updatePlaylist(movie: Movie, playlist: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePlaylistUseCase(movie, playlist)
        }
    }
}