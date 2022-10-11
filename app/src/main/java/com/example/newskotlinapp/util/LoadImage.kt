package com.example.newskotlinapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("looad")
fun loadImage(view:ImageView,url:String? ){
        url?.let {
                Glide.with(view).load(url).into(view)

        }
}