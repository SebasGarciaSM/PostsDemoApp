package com.example.postsdemoapp.data.repositores_impl

import com.example.postsdemoapp.data.Mappers.toDomain
import com.example.postsdemoapp.data.models.PostsResponseItem
import com.example.postsdemoapp.data.services.PostsApiService
import com.example.postsdemoapp.domain.models.Post
import com.example.postsdemoapp.domain.repositories.IPostsRepository

class PostsRepositoryImpl(
    val api: PostsApiService
) : IPostsRepository {
    override suspend fun getPosts(): List<Post> {
        return try {
            val response = api.getPosts()
            val listOfPosts = mutableListOf<Post>()

            if (response.isSuccessful) {
                val body = response.body()

                body?.let { posts ->
                    posts.filter {
                        listOfPosts.add(it.toDomain())
                    }
                }

                listOfPosts
            } else {
                throw Exception("No se pudo obtener info")
            }
        } catch (e: Exception) {
            throw Exception(e.toString())
        }
    }
}