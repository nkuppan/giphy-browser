package com.nkuppan.giphybrowser.presentation.searchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nkuppan.giphybrowser.core.extension.loadNetworkImageWithStatusCallback
import com.nkuppan.giphybrowser.core.ui.fragment.BaseBindingFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphyImageBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger

@AndroidEntryPoint
class GiphyImageFragment : BaseBindingFragment<FragmentGiphyImageBinding>() {

    private val args: GiphyImageFragmentArgs by navArgs()

    private val image: GiphyImage by lazy { args.image }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        loadImage()
    }

    private fun loadImage() {

        binding.loader.show()

        loadNetworkImageWithStatusCallback(
            requireContext(),
            binding.giphyImageView,
            image.original.url
        ) { status ->
            if (isAdded) {
                binding.loader.hide()
                Logger.getGlobal().warning("Status of the glide loading $status")
            }
        }
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentGiphyImageBinding {
        return FragmentGiphyImageBinding.inflate(inflater, container, false)
    }
}
