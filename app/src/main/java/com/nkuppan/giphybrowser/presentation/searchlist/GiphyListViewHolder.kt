package com.nkuppan.giphybrowser.presentation.searchlist

import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.nkuppan.giphybrowser.databinding.ListItemGiphyBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage

class GiphyListViewHolder(private val binding: ListItemGiphyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val set = ConstraintSet()

    fun bind(
        image: GiphyImage,
        clickCallback: (GiphyImage: GiphyImage) -> Unit
    ) {
        binding.apply {

            title.text = image.title

            val fixedHeightImage = image.thumbnail
            binding.imageUrl = fixedHeightImage.url

            set.clone(parentConstraint)
            set.setDimensionRatio(giphyImageView.id, fixedHeightImage.getHeightAnWidth())
            set.applyTo(parentConstraint)

            root.setOnClickListener {
                clickCallback.invoke(image)
            }
        }
    }
}
