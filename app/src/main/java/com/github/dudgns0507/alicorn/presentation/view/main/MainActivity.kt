package com.github.dudgns0507.alicorn.presentation.view.main

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.github.dudgns0507.alicorn.R
import com.github.dudgns0507.alicorn.core.BaseActivity
import com.github.dudgns0507.alicorn.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val vm: MainViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun initBinding(): Unit = with(binding) {
    }

    override fun initListener(): Unit = with(binding) {

    }

    override fun initObserve(): Unit = with(vm) {

    }

    override fun afterBinding() {

    }
}