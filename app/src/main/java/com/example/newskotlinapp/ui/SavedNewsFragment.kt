package com.example.newskotlinapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newskotlinapp.R
import com.example.newskotlinapp.adapters.BreakingNewsPagingAdapter
import com.example.newskotlinapp.databinding.FragmentSavedBinding
import com.example.newskotlinapp.databinding.FragmentSearchNewsBinding
import com.example.newskotlinapp.mvvm.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_saved.*

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

   lateinit var  binding :FragmentSavedBinding
    val newsViewModel:NewsViewModel by viewModels()
     var newsPagingAdapter= BreakingNewsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecycler()

        newsViewModel.getSavedArticles().observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)
            Log.v("saved",it.toString())


        }
      /* newsViewModel.getSavedArticles().observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)

            Log.v("saved",it.toString())


        }*/
    }

    private fun setRecycler() {
        binding.rvSavedNews.apply {
            adapter=newsPagingAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}