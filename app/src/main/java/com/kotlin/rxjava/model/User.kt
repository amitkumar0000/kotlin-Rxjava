package com.kotlin.rxjava.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kotlin.rxjava.BR

class User :BaseObservable(){
    var name:ObservableField<String>
    var email:String = ""
        @Bindable
        get
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }
    lateinit var profileImage:String
        @Bindable
        get
    var about:ObservableField<String>

    var noOfFollower:ObservableField<Long>
    var noOfPost:ObservableField<Long>
    var noOfFollowing:ObservableField<Long>

    init {
        name = ObservableField()
        about = ObservableField()

        noOfFollower = ObservableField()
        noOfPost = ObservableField()
        noOfFollowing = ObservableField()
    }

    @BindingAdapter("profileImage")
    public fun loadImage(imageView: ImageView,imageUrl:String){
        Glide.with(imageView.context).load(imageUrl).into(imageView)
    }


}

