package com.nkuppan.giphybrowser.presentation.searchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nkuppan.giphybrowser.databinding.ListItemGiphyBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage

class GiphyListAdapter(
    private val clickCallback: (image: GiphyImage) -> Unit
) : PagingDataAdapter<GiphyImage, GiphyListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GiphyListViewHolder(
        ListItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: GiphyListViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, clickCallback)
        }
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
