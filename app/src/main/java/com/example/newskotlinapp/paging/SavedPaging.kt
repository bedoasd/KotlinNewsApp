package com.example.newskotlinapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newskotlinapp.api.NewsApi
import com.example.newskotlinapp.models.Article
import com.example.newskotlinapp.repo.NewsRepository
import com.example.newskotlinapp.util.Constants

class SavedPaging(val newsRepository: NewsRepository) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {

        return null
    /*state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
    }*/

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page=params.key?:1

        return try {

            val data=newsRepository.getAllSavedArticles()
            Log.v("saveddd", data.value.toString())
            LoadResult.Page(
                data= data.value!!,
                prevKey = if(page==1) null else page-1,
                nextKey = if (data.value!!.isEmpty()) null else page+1
            )

        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}