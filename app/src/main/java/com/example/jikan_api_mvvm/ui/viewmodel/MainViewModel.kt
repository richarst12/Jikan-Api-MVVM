package com.example.jikan_api_mvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikan_api_mvvm.data.local.AnimeEntity
import com.example.jikan_api_mvvm.data.repo.AnimeRepository
import com.example.jikan_api_mvvm.data.model.anime_detail.AnimeDetailResponse
import com.example.jikan_api_mvvm.data.model.character.CharactersResponse
import com.example.jikan_api_mvvm.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _topAnime = MutableStateFlow<UiState<List<AnimeEntity>>>(UiState.Loading)
    val topAnime: StateFlow<UiState<List<AnimeEntity>>> = _topAnime

    private val _animeDetail = MutableStateFlow<UiState<AnimeDetailResponse>>(UiState.Loading)
    val animeDetail: StateFlow<UiState<AnimeDetailResponse>> = _animeDetail

    private val _characters = MutableStateFlow<UiState<CharactersResponse>>(UiState.Loading)
    val characters: StateFlow<UiState<CharactersResponse>> = _characters

    fun loadTopAnime() {
        viewModelScope.launch {
            _topAnime.value = UiState.Loading
            try {
                val data = repository.getTopAnime()
                _topAnime.value = UiState.Success(data)
            } catch (e: Exception) {
                _topAnime.value = UiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }

    fun loadAnimeDetail(id: Int) {
        viewModelScope.launch {
            _animeDetail.value = UiState.Loading
            try {
                val response = repository.getAnimeDetail(id)
                _animeDetail.value = UiState.Success(response)
            } catch (e: Exception) {
                _animeDetail.value = UiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }

    fun loadCharacters(id: Int) {
        viewModelScope.launch {
            _characters.value = UiState.Loading
            try {
                val response = repository.getCharacters(id)
                _characters.value = UiState.Success(response)
            } catch (e: Exception) {
                _characters.value = UiState.Error(e.localizedMessage ?: "Error")
            }
        }
    }
}
