package com.example.newsapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val title: String?,
        val url: String,
        val urlToImage: String?,
        val created: Long? = System.currentTimeMillis()
): Parcelable