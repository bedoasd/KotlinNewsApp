package com.example.newskotlinapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val number:Int,
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val source: Source?=null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
) : Serializable