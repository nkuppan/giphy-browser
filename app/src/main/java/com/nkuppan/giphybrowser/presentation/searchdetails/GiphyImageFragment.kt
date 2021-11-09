package com.nkuppan.giphybrowser.presentation.searchdetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.autoCleared
import com.nkuppan.giphybrowser.core.ui.fragment.BaseFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphyImageBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiphyImageFragment : BaseFragment() {

    private val args: GiphyImageFragmentArgs by navArgs()

    private val image: GiphyImage by lazy { args.image }

    private var binding: FragmentGiphyImageBinding by autoCleared()

    private val set = ConstraintSet()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGiphyImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationIcon(R.drawable.ic_back_navigation)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        loadImage()
    }

    private fun loadImage() {

        val fixedHeightImage = image.original

        binding.loader.show()

        Glide
            .with(this)
            .setDefaultRequestOptions(
                RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
            )
            .load(fixedHeightImage.url)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.image_place_holder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.loader.hide()
                    //TODO show retry layout here
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.loader.hide()
                    return false
                }

            })
            .into(binding.giphyImageView)
    }
}
