package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.databinding.ListItemGiphyBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage

class GiphyListViewHolder(private val binding: ListItemGiphyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val set = ConstraintSet()

    fun bind(
        imageDto: GiphyImage,
        onClick: (GiphyImage: GiphyImage) -> Unit
    ) {
        binding.apply {

            title.text = imageDto.title

            val fixedHeightImage = imageDto.thumbnail

            Glide
                .with(giphyImageView.context)
                .setDefaultRequestOptions(
                    RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
                )
                .load(fixedHeightImage.url)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.image_place_holder)
                .into(giphyImageView)

            val ratio = String.format("%s:%s", fixedHeightImage.width, fixedHeightImage.height)
            set.clone(parentConstraint)
            set.setDimensionRatio(giphyImageView.id, ratio)
            set.applyTo(parentConstraint)

            root.setOnClickListener {
                onClick.invoke(imageDto)
            }
        }
    }
}
