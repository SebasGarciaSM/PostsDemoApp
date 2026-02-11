package com.example.postsdemoapp.data.services

import com.example.postsdemoapp.data.models.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsApiService {
    @GET("posts")
    suspend fun getPosts(): Response<PostsResponse>
}