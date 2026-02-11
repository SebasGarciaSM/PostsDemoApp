package com.example.postsdemoapp.data.models


import com.google.gson.annotations.SerializedName

data class PostsResponseItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)