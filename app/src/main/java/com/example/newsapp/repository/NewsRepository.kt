package com.example.newsapp.repository

import com.example.newsapp.api.RetrofitInstance
import com.example.newsapp.local_db.ArticleDatabase
import com.example.newsapp.models.Article

class NewsRepository(
    private val db: ArticleDatabase
) {
    suspend fun getNews(countryCode: String) =
        RetrofitInstance.api.getNews(countryCode)

    suspend fun searchNews(searchQuery: String) =
        RetrofitInstance.api.getSearchedNews(searchQuery)

    suspend fun upsertArticle(article: Article) =
        db.getArticleDao().upsertArticle(article)

    suspend fun deleteArticle(article: Article) =
        db.getArticleDao().deleteArticle(article)

    fun getSavedArticles() =
        db.getArticleDao().getSavedArticles()
}