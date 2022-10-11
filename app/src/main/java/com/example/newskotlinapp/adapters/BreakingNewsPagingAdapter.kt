package com.example.newskotlinapp.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newskotlinapp.BR
import com.example.newskotlinapp.databinding.ItemArticlePreviewBinding
import com.example.newskotlinapp.models.Article
import dagger.hilt.android.qualifiers.ApplicationContext
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
    val differ = AsyncListDiffer(this, Diff_Utils)


    inner class MyViewHolder(var previewBinding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(previewBinding.root){}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      //  val article = Diff_Utils.[position]
        val data =getItem(position)
        holder.previewBinding.setVariable(BR.article,data)

       holder.previewBinding.root.setOnClickListener {

            onItemClickListener?.let { it(data!!) }
        }
            /*holder.itemView.apply {
                Glide.with(this).load(data.urlToImage).into(ivArticleImage)

                tvTitle.text = data.title
                tvDescription.text = data.description
                tvPublishedAt.text = data.publishedAt
                tvSource.text = data.source.name


               setOnClickListener {

                onItemClickListener?.let { it(data!!) }
            }
        }*/

        }

   /* override fun getItemCount(): Int {
        return differ.currentList.size
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val binding=ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    private var onItemClickListener : ((Article)->Unit )?=null
    fun setOnItemClickListener(listener: (Article)->Unit){
        onItemClickListener=listener
    }

}


