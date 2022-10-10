package com.example.newskotlinapp.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.newskotlinapp.api.NewsApi
import com.example.newskotlinapp.models.NewsResponse
import com.example.newskotlinapp.paging.NewsPaging
import com.example.newskotlinapp.paging.SearchedNewsPaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val newsApi: NewsApi): ViewModel() {


    //Get All Breaking News .
    val list= Pager(PagingConfig(pageSize = 10)){
        NewsPaging(newsApi)
    }.flow.cachedIn(viewModelScope)


    //GEt All Searched News .

    private val query= MutableLiveData<String>()

    val searchedList=query.switchMap { query ->
        Pager(PagingConfig(10)){
            SearchedNewsPaging(query, newsApi)
        }.liveData.cachedIn(viewModelScope)
    }

    fun setQuery(s:String){

        query.postValue(s)

    }


}
