package com.example.postsdemoapp.ui.viewmodels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsdemoapp.domain.repositories.IPostsRepository
import com.example.postsdemoapp.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostsViewModel(
    val repository: IPostsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading) // SETTER
    val uiState = _uiState.asStateFlow() // GETTER

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val data = repository.getPosts()
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.toString())
            }
        }
    }

}