package com.nkuppan.giphybrowser.presentation.searchlist

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.EventObserver
import com.nkuppan.giphybrowser.core.extension.clearFocusAndHideKeyboard
import com.nkuppan.giphybrowser.core.extension.showSnackBarMessage
import com.nkuppan.giphybrowser.core.ui.fragment.BaseBindingFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphyBrowseListBinding
import com.nkuppan.giphybrowser.domain.model.GiphyImage
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyBrowserListFragment : BaseBindingFragment<FragmentGiphyBrowseListBinding>() {

    private val args: GiphyBrowserListFragmentArgs by navArgs()

    private val type: Type by lazy { args.type }

    private val giphySearchViewModel: GiphySearchViewModel<GiphyImage> by lazy {
        when (type) {
            Type.GIF -> viewModels<GifSearchViewModel>().value
            Type.STICKERS -> viewModels<StickersSearchViewModel>().value
        }
    }

    /**
     * Using this search view model as a shared view model for multiple fragments
     */
    private val searchViewModel: SearchViewModel by activityViewModels()

    private val adapter = GiphyListAdapter { giphyImage ->
        viewLifecycleOwner.lifecycleScope.launch {
            findNavController().navigate(
                GiphyBrowserListFragmentDirections.actionGiphyBrowseListToGiphyImageFragment(
                    giphyImage
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giphySearchViewModel.refreshSearchWithQuery(searchViewModel.queryString.value ?: "")
        setupViewModel()
        setupSwipeRefresh()
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupSearchView() {

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
                if (giphySearchViewModel.refreshSearchWithQuery(it)) {
                    adapter.refresh()
                }
            }
        )
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                giphySearchViewModel.getPagingResult().collect {
                    //Can remove the lifecycle as an input to the adapter submit data api. Since we are
                    //using the coroutine scope and flows
                    adapter.submitData(it)
                }
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        adapter.addLoadStateListener { combinedLoadStates ->
            when (combinedLoadStates.refresh) {
                is LoadState.Loading -> {
                    binding.retry.isVisible = false
                    if (adapter.itemCount == 0)
                        binding.loader.show()
                    else
                        binding.loader.hide()
                }
                is LoadState.NotLoading -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.retry.isVisible = false
                    binding.loader.hide()
                }
                is LoadState.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    binding.retry.isVisible = adapter.itemCount == 0
                    binding.loader.hide()
                    if (adapter.itemCount > 0) {
                        binding.root.showSnackBarMessage(R.string.failed_to_refresh_giphy)
                    }
                }
            }
        }

        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    private fun handleSearchAction() {
        if (searchViewModel.processQuery()) {
            binding.searchContainer.query.clearFocusAndHideKeyboard()
        } else {
            binding.root.showSnackBarMessage(R.string.enter_valid_query_string)
        }
    }

    override fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentGiphyBrowseListBinding {
        return FragmentGiphyBrowseListBinding.inflate(inflater, container, false)
    }

    override fun bindData(binding: FragmentGiphyBrowseListBinding) {
        super.bindData(binding)
        binding.searchViewModel = searchViewModel
    }
}
