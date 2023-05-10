package com.example.newsapp.local_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article
import com.example.newsapp.util.DATABASE_NAME

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile // any update done in INSTANCE it will notify to all threads where INSTANCE is used
        private var INSTANCE: ArticleDatabase? = null

        fun getDatabase(context: Context): ArticleDatabase {
            if (INSTANCE == null) {
                synchronized(this) { // all threads will get same instance not different
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}