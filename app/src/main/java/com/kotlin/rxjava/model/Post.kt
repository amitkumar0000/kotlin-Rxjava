package com.kotlin.rxjava.model

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class Post {
    var imageUrl:String?=null

    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, imageUrl:String){
        Glide.with(imageView.context).load(imageUrl)
            .into(imageView)
    }


}