package com.example.newskotlinapp.repo

import com.example.newskotlinapp.db.ArticleDao
import com.example.newskotlinapp.db.ArticleDatabase
import com.example.newskotlinapp.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor (val db: ArticleDatabase)
{

    //saved articles

    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)

    fun getAllSavedArticles()=db.getArticleDao().getAllSavedArticles()

    suspend fun delete(article: Article)=db.getArticleDao().deleteAnArticle(article)

}