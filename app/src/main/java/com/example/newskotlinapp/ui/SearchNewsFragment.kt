package com.example.newskotlinapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapp.R
import com.example.newskotlinapp.adapters.BreakingNewsPagingAdapter
import com.example.newskotlinapp.databinding.FragmentBreakingNewsBinding
import com.example.newskotlinapp.databinding.FragmentSearchNewsBinding
import com.example.newskotlinapp.mvvm.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {
    lateinit var binding: FragmentSearchNewsBinding

    val searchViewModel: NewsViewModel by viewModels()
     var searchedAdapter= BreakingNewsPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        searchedAdapter.setOnItemClickListener {
            val bundell=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment2_to_articalNewsFragment,bundell
            )
        }

        setRecycler()

        binding.etSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchViewModel.setQuery(it)
                }
            return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchViewModel.searchedList.observe(viewLifecycleOwner){
            searchedAdapter.submitData(lifecycle,it)

        }
       /* lifecycleScope. launch {
            searchViewModel.searchedList.collect { pagingData ->
                searchedAdapter.submitData(pagingData)
            }
        }*/
    }


    private fun setRecycler() {
        binding.rvSearchNews.apply {
            adapter = searchedAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}












