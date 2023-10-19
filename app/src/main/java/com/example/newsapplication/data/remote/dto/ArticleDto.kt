package com.example.newsapplication.data.remote.dto

import androidx.room.PrimaryKey
import com.example.newsapplication.data.local.entity.ArticleEntity

data class ArticleDto(
        @PrimaryKey
        val url: String,
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val source: SourceDto,
        val title: String?,
        val urlToImage: String?,
        val created: Long? = System.currentTimeMillis()
)

fun ArticleDto.toArticleEntity(): ArticleEntity {
        return ArticleEntity(
                url = this.url,
                content = this.content,
                description = this.description,
                publishedAt = this.publishedAt,
                source = this.source.toSource(),
                title = this.title,
                urlToImage = this.urlToImage,
                created = this.created ?: System.currentTimeMillis()
        )
}