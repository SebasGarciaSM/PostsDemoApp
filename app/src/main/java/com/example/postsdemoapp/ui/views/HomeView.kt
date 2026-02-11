package com.example.postsdemoapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.postsdemoapp.ui.state.UiState
import com.example.postsdemoapp.ui.viewmodels.PostsViewModel

@Composable
fun HomeView(viewModel: PostsViewModel) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeViewContent(
        uiState = uiState.value,
    )
}

@Composable
private fun HomeViewContent(
    modifier: Modifier = Modifier,
    uiState: UiState
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (uiState) {
                UiState.Loading -> CircularProgressIndicator()
                is UiState.Error -> Text(uiState.message)
                is UiState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(uiState.data) { post ->
                            ListItem(
                                headlineContent = { Text(post.title ?: "") },
                                trailingContent = { Text(post.id.toString()) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeViewPreview(modifier: Modifier = Modifier) {
    HomeViewContent(
        uiState = UiState.Loading
    )
}