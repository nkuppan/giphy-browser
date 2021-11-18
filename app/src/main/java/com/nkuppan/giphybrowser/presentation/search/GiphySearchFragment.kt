package com.nkuppan.giphybrowser.presentation.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.EventObserver
import com.nkuppan.giphybrowser.core.extension.autoCleared
import com.nkuppan.giphybrowser.core.extension.clearFocusAndHideKeyboard
import com.nkuppan.giphybrowser.core.extension.showSnackBarMessage
import com.nkuppan.giphybrowser.core.ui.fragment.BaseFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphySearchBinding
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.presentation.theme.ThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphySearchFragment : BaseFragment() {

    private var binding: FragmentGiphySearchBinding by autoCleared()

    private val themeViewModel: ThemeViewModel by activityViewModels()

    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGiphySearchBinding.inflate(inflater, container, false)
        binding.searchViewModel = searchViewModel
        binding.themeViewModel = themeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchContainer.query.apply {
            setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handleSearchAction()
                    true
                } else {
                    false
                }
            }

            setOnKeyListener { _, _, event ->
                if (event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    handleSearchAction()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        searchViewModel.searchThisQuery.observe(
            viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(
                    GiphySearchFragmentDirections.actionGiphySearchToGiphyBrowseList(
                        it,
                        if (binding.gif.isChecked) Type.GIF else Type.STICKERS
                    )
                )
            }
        )

        themeViewModel.openThemeDialog.observe(viewLifecycleOwner, EventObserver {
            lifecycleScope.launch {
                val theme = themeViewModel.theme.first()
                findNavController().navigate(
                    GiphySearchFragmentDirections.actionGiphySearchToTheme(
                        theme
                    )
                )
            }
        })
    }

    private fun handleSearchAction() {
        if (searchViewModel.processQuery()) {
            binding.searchContainer.query.clearFocusAndHideKeyboard()
        } else {
            binding.root.showSnackBarMessage(R.string.enter_valid_query_string)
        }
    }
}
