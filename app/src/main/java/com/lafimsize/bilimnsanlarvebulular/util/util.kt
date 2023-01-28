package com.lafimsize.bilimnsanlarvebulular.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadWithGlide(url:String){

    Glide.with(context)
        .load(url)
        .into(this)


}