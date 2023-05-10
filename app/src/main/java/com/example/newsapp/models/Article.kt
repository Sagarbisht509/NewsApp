package com.example.newsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.util.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)