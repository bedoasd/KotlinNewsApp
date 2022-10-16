package com.example.newskotlinapp.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newskotlinapp.api.NewsApi
import com.example.newskotlinapp.models.Article
import com.example.newskotlinapp.paging.NewsPaging
import com.example.newskotlinapp.paging.SearchedNewsPaging
import com.example.newskotlinapp.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor( val newsApi: NewsApi , val newsRepository: NewsRepository): ViewModel() {


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

    fun saveArticle(article: Article)=viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedArticles() = newsRepository.getAllSavedArticles()





    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.delete(article)
    }

}
