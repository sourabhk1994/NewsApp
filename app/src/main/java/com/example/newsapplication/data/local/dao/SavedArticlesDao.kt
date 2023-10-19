package com.example.newsapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedArticlesDao {
    @Query(
        "SELECT * FROM news_articles ORDER BY created DESC"
    )
    fun getSavedArticlesByDateLatest(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM news_articles WHERE title LIKE :queryString OR description LIKE :queryString ")
    fun getSavedArticlesByName(queryString: String): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM news_articles ORDER BY title Asc ")
    fun getSavedArticlesNameAsc(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM news_articles ORDER BY source Asc ")
    fun getSavedArticlesSourceAsc(): Flow<List<ArticleEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(articleEntity: ArticleEntity)

    @Query("DELETE FROM news_articles WHERE url LIKE :newsUrl")
    suspend fun deleteNewsArticle(newsUrl: String)
}