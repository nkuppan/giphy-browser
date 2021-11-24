package com.nkuppan.giphybrowser.data.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.webkit.URLUtil
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nkuppan.giphybrowser.data.R


private fun loadErrorImage(imageView: ImageView) {
    Glide
        .with(imageView.context)
        .load(R.drawable.ic_error)
        .placeholder(R.drawable.image_place_holder)
        .into(imageView)
}

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
        loadErrorImage(imageView)
    }
}

/**
 * Loading images with status callback
 *
 * @param context to initialize Glide framework
 * @param imageView to set loaded image to the view & load image efficiently depending on the image size
 * @param networkImageUrl image url to fetch from server
 * @param statusCallback sending back status to the caller to handle UI functionalities
 */
fun loadNetworkImageWithStatusCallback(
    context: Context,
    imageView: ImageView,
    networkImageUrl: String?,
    statusCallback: ((Boolean) -> Unit)? = null
) {
    if (URLUtil.isValidUrl(networkImageUrl)) {
        Glide
            .with(context)
            .setDefaultRequestOptions(
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
            )
            .load(networkImageUrl)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.image_place_holder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    statusCallback?.invoke(false)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    statusCallback?.invoke(true)
                    return false
                }

            })
            .into(imageView)
    } else {
        loadErrorImage(imageView)
    }
}