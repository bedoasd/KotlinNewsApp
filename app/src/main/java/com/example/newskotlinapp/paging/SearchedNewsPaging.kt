package com.example.newskotlinapp.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newskotlinapp.api.NewsApi
import com.example.newskotlinapp.models.Article
import com.example.newskotlinapp.util.Constants

class SearchedNewsPaging(val s:String ,val newsApi: NewsApi) : PagingSource<Int, Article>() {


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {

        return state.anchorPosition?.let {
            val anchorPage=state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
    }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {

        val page=params.key?:1

        return try {

            val data=newsApi.getSearchedNews(s, "2022-10-09" , "2022-10-09","popularity",Constants.API_KEY,page)
            Log.e("searchnews",data.body().toString())
            LoadResult.Page(
                data= data.body()?.articles!!,
                prevKey = if(page==1) null else page-1,
                nextKey = if (data.body()?.articles?.isEmpty()!!) null else page+1
            )

        }catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }
}