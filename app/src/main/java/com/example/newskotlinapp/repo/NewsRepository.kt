package com.example.newskotlinapp.repo

import com.example.newskotlinapp.db.ArticleDao
import com.example.newskotlinapp.db.ArticleDatabase
import com.example.newskotlinapp.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor (val db: ArticleDao)
{

    //saved articles

    suspend fun upsert(article: Article)=db.upsert(article)

    fun getAllSavedArticles()=db.getAllSavedArticles()

    suspend fun delet(article: Article)=db.deleteAnArticle(article)

}