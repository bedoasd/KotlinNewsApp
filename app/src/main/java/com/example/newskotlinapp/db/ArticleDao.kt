package com.example.newskotlinapp.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.room.*
import com.example.newskotlinapp.models.Article

@Dao
interface ArticleDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllSavedArticles():LiveData<List<Article>>

    @Delete
  suspend  fun deleteAnArticle(article: Article)


}