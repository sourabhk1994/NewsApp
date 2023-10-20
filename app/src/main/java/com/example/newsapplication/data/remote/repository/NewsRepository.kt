package com.example.newsapplication.data.remote.repository

import androidx.paging.*
import com.example.newsapplication.data.local.entity.ArticleEntity
import com.example.newsapplication.data.remote.api.ApiService
import com.example.newsapplication.data.remote.datasource.NewsPagingSource
import com.example.newsapplication.data.remote.dto.toArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val service: ApiService,
) {

    fun getSearchResultStream(
        query: String?
    ): Flow<PagingData<ArticleEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    service,
                    query
                )
            }
        ).flow.map {
            it.map { data ->
                data.toArticleEntity()
            }
        }
    }
}