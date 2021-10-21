package com.nkuppan.giphybrowser.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Base binding fragment and extracted the common binding variable here and managed it
 */
abstract class BaseBindingFragment<T : ViewBinding> : BaseFragment() {

    private var _binding: ViewBinding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = dataBindingInflater(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract var dataBindingInflater: (LayoutInflater) -> ViewBinding
}
