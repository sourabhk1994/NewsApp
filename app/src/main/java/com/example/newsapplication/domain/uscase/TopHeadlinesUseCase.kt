package com.example.newsapplication.domain.uscase

import androidx.paging.PagingData
import com.example.newsapplication.data.local.entity.ArticleEntity
import com.example.newsapplication.data.remote.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesUseCase @Inject constructor(private val allNewsRepository: NewsRepository) {

    operator fun invoke(query: String?): Flow<PagingData<ArticleEntity>> {
        return allNewsRepository.getSearchResultStream(query)
    }
}