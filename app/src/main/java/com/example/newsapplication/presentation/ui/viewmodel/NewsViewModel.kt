package com.example.newsapplication.presentation.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.newsapplication.data.local.entity.toArticle
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.uscase.TopHeadlinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val topHeadLinesUseCase: TopHeadlinesUseCase,
) : ViewModel() {
    init {
        topHeadlinesFlow()
    }
    var isLoading = true
    fun topHeadlinesFlow(): Flow<PagingData<Article>> =
        topHeadLinesUseCase(null).cachedIn(viewModelScope).map {
            it.map { data ->
                data.toArticle()
            }
        }
}

