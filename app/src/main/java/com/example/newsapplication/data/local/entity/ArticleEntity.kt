package com.example.newsapplication.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapplication.domain.model.Article
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news_articles")
@Parcelize
data class ArticleEntity(
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val source: Source,
        val title: String?,
        @PrimaryKey
        val url: String,
        val urlToImage: String?,
        val created: Long? = System.currentTimeMillis()
): Parcelable

fun ArticleEntity.toArticle(): Article {
        return Article(
                url = this.url,
                content = this.content,
                description = this.description,
                publishedAt = this.publishedAt,
                title = this.title,
                urlToImage = this.urlToImage,
                created = this.created ?: System.currentTimeMillis()
        )
}