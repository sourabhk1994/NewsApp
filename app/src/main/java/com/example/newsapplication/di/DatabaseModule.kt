package com.example.newsapplication.di

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.local.database.SavedArticlesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application)
            = Room.databaseBuilder(application, SavedArticlesDatabase::class.java, "article_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideRecentArticleDao(articlesDatabase: SavedArticlesDatabase) =
        articlesDatabase.savedArticlesDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun providesApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope