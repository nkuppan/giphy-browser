package com.nkuppan.giphybrowser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nkuppan.giphybrowser.browser.R
import com.nkuppan.giphybrowser.browser.model.Type
import com.nkuppan.giphybrowser.browser.ui.viewmodel.SearchViewModel
import com.nkuppan.giphybrowser.core.extension.EventObserver
import com.nkuppan.giphybrowser.core.extension.autoCleared
import com.nkuppan.giphybrowser.core.extension.clearFocusAndHideKeyboard
import com.nkuppan.giphybrowser.core.extension.showSnackBarMessage
import com.nkuppan.giphybrowser.core.ui.fragment.BaseFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphySearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GiphySearchFragment : BaseFragment() {

    private var binding: FragmentGiphySearchBinding by autoCleared()

    private val viewModel: SearchViewModel by lazy {
        viewModels<SearchViewModel>().value
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGiphySearchBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchContainer.query.apply {
            setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (viewModel.processQuery()) {
                        clearFocusAndHideKeyboard()
                    } else {
                        binding.root.showSnackBarMessage(R.string.enter_valid_query_string)
                    }
                    true
                } else {
                    false
                }
            }
        }

        viewModel.searchThisQuery.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                GiphySearchFragmentDirections.actionGiphySearchToGiphyBrowseList(
                    it,
                    if (binding.gif.isChecked) Type.GIF else Type.STICKERS
                )
            )
        })
    }
}
