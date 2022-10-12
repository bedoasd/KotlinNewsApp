package com.example.newskotlinapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newskotlinapp.di.ApplicationScope
import com.example.newskotlinapp.models.Article
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Scope



@Database(entities = [Article::class], version =1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    class Callback @Inject constructor(
        private val database: Provider<ArticleDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}

/*

companion object{ //Make  instance
    @Volatile
    private var instance :ArticleDatabase?=null
    private val lock=Any()

    @Provides
    @Singleton
    operator fun invoke(context: Context)= instance ?: synchronized(lock){
        instance ?: createDatabase(context).also { instance = it }
    }

    @Provides
    @Singleton
    private fun createDatabase(context: Context)=Room.databaseBuilder(
        context.applicationContext,
        ArticleDatabase::class.java,
        "article_db.db"
    ).build()
}*/
