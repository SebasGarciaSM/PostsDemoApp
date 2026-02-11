package com.example.postsdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.postsdemoapp.data.repositores_impl.PostsRepositoryImpl
import com.example.postsdemoapp.data.services.PostsApiService
import com.example.postsdemoapp.domain.repositories.IPostsRepository
import com.example.postsdemoapp.ui.theme.PostsDemoAppTheme
import com.example.postsdemoapp.ui.viewmodels.PostsViewModel
import com.example.postsdemoapp.ui.views.HomeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(PostsApiService::class.java)

        setContent {
            PostsDemoAppTheme {
                HomeView(
                    viewModel = PostsViewModel(
                        repository = PostsRepositoryImpl(
                            api = api
                        )
                    )
                )
            }
        }
    }
}