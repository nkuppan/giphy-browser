package com.nkuppan.giphybrowser.presentation

import android.os.Bundle
import com.nkuppan.giphybrowser.R
import com.nkuppan.giphybrowser.core.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
