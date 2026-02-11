package com.example.postsdemoapp.data

import com.example.postsdemoapp.data.models.PostsResponseItem
import com.example.postsdemoapp.domain.models.Post

object Mappers {
    fun PostsResponseItem.toDomain(): Post {
        return Post(
            body = this.body,
            id = this.id,
            title = this.title,
            userId = this.userId
        )
    }
}