package com.tooploox.utils.imageloader

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tooploox.R
import com.tooploox.utils.TooplooxImageLoader

class GlideImageLoader constructor(var context: Context) : TooplooxImageLoader {


    override fun loadInto(url: String?, imageView: ImageView?) {
        Glide.with(context).load(url).asBitmap()
            .format(DecodeFormat.PREFER_ARGB_8888)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .placeholder(ContextCompat.getDrawable(imageView!!.context, R.drawable.ic_placeholder))
            .fitCenter().into(imageView)
    }
}
