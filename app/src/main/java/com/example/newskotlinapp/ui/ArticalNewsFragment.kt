package com.example.newskotlinapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.example.newskotlinapp.R
import com.example.newskotlinapp.databinding.FragmentArticalNewsBinding
import com.example.newskotlinapp.databinding.FragmentBreakingNewsBinding
import com.example.newskotlinapp.databinding.ItemArticlePreviewBinding.inflate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_artical_news.*

@AndroidEntryPoint
class ArticalNewsFragment : Fragment(R.layout.fragment_artical_news) {

    lateinit var binding:FragmentArticalNewsBinding

    val args: ArticalNewsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article!!.url)
            }


    }
}