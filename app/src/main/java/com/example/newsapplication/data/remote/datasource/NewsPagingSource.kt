package com.example.newsapplication.data.remote.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapplication.data.remote.api.ApiService
import com.example.newsapplication.data.remote.dto.ArticleDto
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 1
const val NETWORK_PAGE_SIZE = 10

class NewsPagingSource @Inject constructor(
    private val newsApiService: ApiService,
    private val query: String?
) :
    PagingSource<Int, ArticleDto>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query
        return if (apiQuery != null) {
            try {
                val response =
                    newsApiService.searchNewsArticles(apiQuery, position, params.loadSize)
                val repos = response.articles
                val nextKey = if (repos.isEmpty()) {
                    null
                } else {
                     if(
                         params.loadSize == 3* NETWORK_PAGE_SIZE
                     )   {
                         position + 1
                     }
                    else {
                         position + (params.loadSize / NETWORK_PAGE_SIZE)
                     }
                }
                Log.d("Search", "$nextKey")

                LoadResult.Page(
                    data = repos,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        } else {
            try {
                val response =
                    newsApiService.getTopHeadlines(
                        position,
                        params.loadSize
                    )
                val repos = response.articles
                val nextKey = if (repos.isEmpty()) {
                    null
                } else {
                    position + (params.loadSize / NETWORK_PAGE_SIZE)
                }
                Log.d("NotSearch", "$nextKey")

                LoadResult.Page(
                    data = repos,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = nextKey
                )
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        }


    }
}