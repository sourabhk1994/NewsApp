package com.example.newsapplication.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewResponseDto(
    @SerializedName("articles")
    val articles: MutableList<ArticleDto>,
    val status: String,
    val totalResults: Int
)