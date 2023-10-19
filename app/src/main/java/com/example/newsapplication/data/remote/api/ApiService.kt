package com.example.newsapplication.data.remote.api

import com.example.newsapplication.data.remote.dto.NewResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything")
    suspend fun searchNewsArticles(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = "436a7b507ee5433bafa1ad67c8eff93b"
        ): NewResponseDto

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("language") language: String? ="en",
        @Query("apiKey") apiKey: String = "436a7b507ee5433bafa1ad67c8eff93b"
        ): NewResponseDto

}