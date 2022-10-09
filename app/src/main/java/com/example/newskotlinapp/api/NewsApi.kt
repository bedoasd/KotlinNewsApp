package com.example.newskotlinapp.api

import com.example.newskotlinapp.util.Constants
import com.example.newskotlinapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi  {

    @GET("top-headlines")
     suspend fun getBreakingNews(
        @Query("country") country:String="gb" ,
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apikey:String=Constants.API_KEY
    ): Response<NewsResponse>



}