package com.example.newsapplication.presentation.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.newsapplication.data.local.entity.toArticle
import com.example.newsapplication.domain.model.QueryWithSort
import com.example.newsapplication.domain.uscase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val state: SavedStateHandle
) : ViewModel() {
    private var currentQuery: MutableStateFlow<String> = MutableStateFlow("latest")
    private val sortParamsFlow = MutableStateFlow("relevancy")
    val searchNewsFlow = combine(
        currentQuery,
        sortParamsFlow,
    ) { (query, sort) ->
        QueryWithSort(query, sort)
    }.flatMapLatest {
        searchUseCase(currentQuery.value)
            .cachedIn(viewModelScope).map {
                it.map { data ->
                    data.toArticle()
                }}
    }
    fun searchNews(query: String) {
        state["query"] = query
        currentQuery.value = state.getLiveData("query", "latest").value.toString()
    }
    override fun onCleared() {
        state["query"] = null
        super.onCleared()
    }
}
