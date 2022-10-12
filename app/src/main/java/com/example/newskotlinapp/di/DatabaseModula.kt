package com.example.newskotlinapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newskotlinapp.db.ArticleDao
import com.example.newskotlinapp.db.ArticleDatabase
import com.example.newskotlinapp.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: ArticleDatabase.Callback): ArticleDatabase{
        return Room
            .databaseBuilder(application, ArticleDatabase::class.java, "news_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideArticleDao(db: ArticleDatabase): ArticleDao{
        return db.getArticleDao()
    }
}