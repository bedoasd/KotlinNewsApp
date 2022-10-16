package com.example.newskotlinapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskotlinapp.R
import com.example.newskotlinapp.adapters.SavedAdapter
import com.example.newskotlinapp.databinding.FragmentSavedBinding
import com.example.newskotlinapp.mvvm.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_saved.*

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

   lateinit var  binding :FragmentSavedBinding
    val newsViewModel:NewsViewModel by viewModels()
    lateinit var savedAdapter:SavedAdapter

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

        savedAdapter.setOnItemClickListener {
            val bundle =Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment2_to_articalNewsFragment,bundle
            )
        }

            val itemByTouchHelperCallback=object :ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN ,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position=viewHolder.absoluteAdapterPosition
                    val article = savedAdapter.differ.currentList[position]

                    newsViewModel.deleteArticle(article)

                    Snackbar.make(view,"Article has deleted Successfully",Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            newsViewModel.saveArticle(article)
                        }
                            show()
                    }
                }

            }
            ItemTouchHelper(itemByTouchHelperCallback).apply {
                attachToRecyclerView(binding.rvSavedNews)
            }




        newsViewModel.getSavedArticles().observe(viewLifecycleOwner){
            savedAdapter.differ.submitList(it)
        }
      /* newsViewModel.getSavedArticles().observe(viewLifecycleOwner){
            newsPagingAdapter.submitData(lifecycle,it)

            Log.v("saved",it.toString())


        }*/
    }

    private fun setRecycler() {
        savedAdapter=SavedAdapter()
        binding.rvSavedNews.apply {
            adapter=savedAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}