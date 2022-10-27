package com.example.jokeapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokeapi.domain.useCase.GetJokesUseCase
import com.example.jokeapi.domain.useCase.StartFetchingJokesUseCase
import com.example.jokeapi.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val startFetchingJokesUseCase: StartFetchingJokesUseCase): ViewModel() {
    private val currentUiState: MutableLiveData<UiState> = MutableLiveData()
    fun getUiStates(): LiveData<UiState> = currentUiState

    fun startFetchingJokes() {
        currentUiState.value = UiState.Progress
        startFetchingJokesUseCase()
        getJokes()
    }

    private fun getJokes() {
        viewModelScope.launch(Dispatchers.IO) {
            getJokesUseCase().collect { result ->
                currentUiState.postValue(UiState.Content(result))
            }
        }
    }
}