package com.nkuppan.giphybrowser.presentation.searchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.extension.EventObserver
import com.nkuppan.giphybrowser.core.extension.autoCleared
import com.nkuppan.giphybrowser.core.extension.clearFocusAndHideKeyboard
import com.nkuppan.giphybrowser.core.extension.showSnackBarMessage
import com.nkuppan.giphybrowser.core.ui.fragment.BaseFragment
import com.nkuppan.giphybrowser.databinding.FragmentGiphyBrowseListBinding
import com.nkuppan.giphybrowser.domain.model.Type
import com.nkuppan.giphybrowser.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GiphyBrowserListFragment : BaseFragment() {

    private val args: GiphyBrowserListFragmentArgs by navArgs()

    private val queryString: String by lazy { args.query }

    private val type: Type by lazy { args.type }

    private var binding: FragmentGiphyBrowseListBinding by autoCleared()

    private val giphySearchViewModel: GiphyBrowserListViewModel by lazy {
        when (type) {
            Type.GIF -> viewModels<GifSearchViewModel>().value
            Type.STICKERS -> viewModels<StickersSearchViewModel>().value
        }
    }

    private val searchViewModel: SearchViewModel by activityViewModels()

    private val adapter = GiphyListAdapter { giphyImage ->
        lifecycleScope.launch {
            findNavController().navigate(
                GiphyBrowserListFragmentDirections.actionGiphyBrowseListToGiphyImageFragment(
                    giphyImage
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGiphyBrowseListBinding.inflate(inflater, container, false)
        binding.searchViewModel = searchViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.updateQuery(queryString)
        giphySearchViewModel.refreshSearchWithQuery(queryString)
        setupViewModel()
        setupSwipeRefresh()
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupSearchView() {

        binding.searchContainer.query.apply {
            setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (searchViewModel.processQuery()) {
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
        lifecycleScope.launch {
            giphySearchViewModel.searchResult.collect {
                //Can remove the lifecycle as an input to the adapter submit data api. Since we are
                //using the coroutine scope and flows
                adapter.submitData(it)
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
}
