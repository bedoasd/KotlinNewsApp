package com.example.newskotlinapp.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.room.*
import com.example.newskotlinapp.models.Article

@Dao
interface ArticleDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //overwrite on database
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllSavedArticles():LiveData<List<Article>>

    @Delete
    fun deleteAnArticle(article: Article)


}