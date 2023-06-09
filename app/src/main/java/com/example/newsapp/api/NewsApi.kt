package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun getSearchedNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}