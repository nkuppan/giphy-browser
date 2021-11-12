package com.nkuppan.giphybrowser.presentation.searchdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.navArgs
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.autoCleared
import com.nkuppan.giphybrowser.core.ui.fragment.BaseFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphyImageBinding
import com.nkuppan.giphybrowser.domain.extension.loadNetworkImageWithStatusCallback
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger

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

        setSupportedActionBar(binding.toolbar)

        loadImage()
    }

    private fun loadImage() {

        binding.loader.show()

        loadNetworkImageWithStatusCallback(
            requireContext(),
            binding.giphyImageView,
            image.original.url
        ) { status ->
            binding.loader.hide()
            Logger.getGlobal().warning("Status of the glide loading $status")
        }
    }
}
