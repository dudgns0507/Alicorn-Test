package com.github.dudgns0507.alicorn.presentation.view.main

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dudgns0507.alicorn.R
import com.github.dudgns0507.alicorn.core.BaseActivity
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.core.observe
import com.github.dudgns0507.alicorn.databinding.ActivityMainBinding
import com.github.dudgns0507.alicorn.presentation.adapter.ChatAdapter
import com.github.dudgns0507.alicorn.presentation.view.chat.ChatActivity
import com.github.dudgns0507.alicorn.presentation.view.chat.ChatBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_main
    override val vm: MainViewModel by viewModels()

    private val chatAdapter = ChatAdapter().apply {
        listener = ItemClickListener { position ->
            val item = getItem(position)
            startActivity(ChatActivity.callingIntent(this@MainActivity, ChatBundle(item.id)))
        }
    }

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun initBinding(): Unit = with(binding) {
        rvChat.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatAdapter
        }
    }

    override fun initListener(): Unit = with(binding) {

    }

    override fun initObserve(): Unit = with(vm) {
        observe(chats) {
            chatAdapter.update(it)
        }
    }

    override fun afterBinding() = Unit

    override fun onResume() {
        super.onResume()
        vm.getChats()
    }
}