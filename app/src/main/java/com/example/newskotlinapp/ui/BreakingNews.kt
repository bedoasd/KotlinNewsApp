package com.example.newskotlinapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newskotlinapp.R
import com.example.newskotlinapp.adapters.BreakingNewsPagingAdapter
import com.example.newskotlinapp.databinding.FragmentBreakingNewsBinding
import com.example.newskotlinapp.mvvm.NewsViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BreakingNews : Fragment() {
    lateinit var binding:FragmentBreakingNewsBinding
    val newsViewModel:NewsViewModel by viewModels()
    val newsAdapter =BreakingNewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentBreakingNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecycler()



            lifecycleScope. launch {
                newsViewModel. list. collect { pagingData ->
                    newsAdapter. submitData(pagingData)
                }


    }
    }
    private fun setRecycler() {
        binding.rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }
}

