package com.nkuppan.giphybrowser.core.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

/**
 * Wrapper fragment class and this will be inherited by all child fragments in the project
 */
open class BaseFragment : Fragment() {

    fun setSupportedActionBar(toolbar: Toolbar) {
        (requireActivity() as AppCompatActivity?)?.let {
            it.setSupportActionBar(toolbar)
            it.setupActionBarWithNavController(findNavController())
        }
    }

    fun setTitle(title: String) {
        (requireActivity() as AppCompatActivity?)?.supportActionBar?.let {
            it.title = title
        }
    }
}
