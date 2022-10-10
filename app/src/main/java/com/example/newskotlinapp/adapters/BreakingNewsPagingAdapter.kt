package com.example.newskotlinapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newskotlinapp.BR
import com.example.newskotlinapp.databinding.ItemArticlePreviewBinding
import com.example.newskotlinapp.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*


class BreakingNewsPagingAdapter :PagingDataAdapter<Article,BreakingNewsPagingAdapter.MyViewHolder>(Diff_Utils) {

    companion object{
        val Diff_Utils=object : DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url==newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem==newItem
            }

        }
    }


    inner class MyViewHolder(val previewBinding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(previewBinding.root){}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

       // holder.previewBinding.setVariable(BR._all,getItem(position)) //check Here"BR"
        val data=getItem(position)
        holder.previewBinding.apply {
            tvTitle.text="${data?.title}"
            tvDescription.text="${data?.description}"
            tvPublishedAt.text="${data?.publishedAt}"
            tvSource.text="${data?.source?.name}"
            Glide.with(ivArticleImage).load(data?.urlToImage).into(ivArticleImage)

        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val binding=ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }


}