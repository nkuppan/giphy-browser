package com.nkuppan.giphybrowser.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding

/**
 * Wrapper fragment class and this will be inherited by all child fragments in the project
 */
abstract class BaseBindingFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = inflateLayout(inflater, container, savedInstanceState)
        binding.apply {
            bindData(this)
        }
        return binding.root
    }

    protected open fun bindData(binding: T) {
        //Do nothing
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun inflateLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T
}