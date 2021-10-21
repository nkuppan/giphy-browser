package com.nkuppan.giphybrowser.browser.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nkuppan.giphybrowser.browser.databinding.ListItemGiphyBinding
import com.nkuppan.giphybrowser.core.domain.model.GiphyImage

class GiphyListAdapter(
    private val onClick: (image: GiphyImage) -> Unit
) : PagingDataAdapter<GiphyImage, GiphyListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GiphyListViewHolder(
        ListItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GiphyListViewHolder, position: Int) {
        val image = getItem(position) ?: return
        holder.bind(image, onClick)
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GiphyImage>() {
            override fun areItemsTheSame(oldItem: GiphyImage, newItem: GiphyImage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: GiphyImage,
                newItem: GiphyImage
            ) = oldItem == newItem
        }
    }
}
