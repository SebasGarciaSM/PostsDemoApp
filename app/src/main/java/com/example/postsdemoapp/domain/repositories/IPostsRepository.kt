package com.example.postsdemoapp.domain.repositories

import com.example.postsdemoapp.domain.models.Post

interface IPostsRepository {
    suspend fun getPosts(): List<Post>
}