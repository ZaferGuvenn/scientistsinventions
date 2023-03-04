package com.lafimsize.bilimnsanlarvebulular.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

fun ImageView.downloadWithGlide(url:String){

    Glide.with(context)
        .load(url)
        .into(this)


}


@BindingAdapter("android:downloadwithglid")
fun downloadImage(view: ImageView,url: String){
    view.downloadWithGlide(url)
}

@BindingAdapter("app:userInputEnabled")
fun userInputForVP(view:ViewPager2,doYouWantIt:Boolean){
    view.isUserInputEnabled=doYouWantIt
}