package com.nkuppan.giphybrowser.domain.extension

import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nkuppan.giphybrowser.domain.R

@BindingAdapter("app:loadNetworkImage")
fun loadNetworkImage(imageView: ImageView, networkImageUrl: String?) {

    if (URLUtil.isValidUrl(networkImageUrl)) {
        Glide
            .with(imageView.context)
            .setDefaultRequestOptions(
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
            )
            .load(networkImageUrl)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.image_place_holder)
            .into(imageView)
    } else {
        Glide
            .with(imageView.context)
            .load(R.drawable.ic_error)
            .placeholder(R.drawable.image_place_holder)
            .into(imageView)
    }
}