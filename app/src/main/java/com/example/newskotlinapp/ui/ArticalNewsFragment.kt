package com.example.newskotlinapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newskotlinapp.R
import com.example.newskotlinapp.databinding.FragmentArticalNewsBinding
import com.example.newskotlinapp.databinding.FragmentSavedBinding
import com.example.newskotlinapp.mvvm.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artical_news.*

@AndroidEntryPoint
class ArticalNewsFragment : Fragment(R.layout.fragment_artical_news) {

    lateinit var binding:FragmentArticalNewsBinding
    val newsViewModel:NewsViewModel by viewModels()
    val args: ArticalNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticalNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article!!.url!!)
            }

        binding.fab.setOnClickListener{
            newsViewModel.saveArticle(article!!)
            Snackbar.make(view,"Article Saved successfully",Snackbar.LENGTH_SHORT).show()
        }

    }
}