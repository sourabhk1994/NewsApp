package com.example.newsapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapplication.data.local.dao.SavedArticlesDao
import com.example.newsapplication.data.local.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SavedArticlesDatabase : RoomDatabase() {
    abstract fun savedArticlesDao(): SavedArticlesDao
}