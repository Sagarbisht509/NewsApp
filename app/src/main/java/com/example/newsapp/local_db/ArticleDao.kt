package com.example.newsapp.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.Article
import com.example.newsapp.util.TABLE_NAME

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertArticle(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article): Long

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllArticles(): LiveData<List<Article>>
}