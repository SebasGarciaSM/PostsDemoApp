package com.example.postsdemoapp.ui.state

import com.example.postsdemoapp.domain.models.Post

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: List<Post>) : UiState()
    data class Error(val message: String) : UiState()
}