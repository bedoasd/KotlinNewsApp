package com.example.newskotlinapp.di

import com.example.newskotlinapp.api.NewsApi
import com.example.newskotlinapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)

object RetrofitInstance  {

    @Provides
    fun provideRetrofitInstance():NewsApi{

        return  Retrofit.Builder().baseUrl(Constants.BASE_URl).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(NewsApi::class.java)

    }

}