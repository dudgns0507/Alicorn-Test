package com.github.dudgns0507.alicorn.presentation.view.chat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dudgns0507.alicorn.R
import com.github.dudgns0507.alicorn.core.BaseActivity
import com.github.dudgns0507.alicorn.core.getDateString
import com.github.dudgns0507.alicorn.core.observe
import com.github.dudgns0507.alicorn.databinding.ActivityChatBinding
import com.github.dudgns0507.alicorn.presentation.adapter.MessageAdapter
import com.github.dudgns0507.alicorn.presentation.adapter.MessageType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatBundle(
    val id: Int
) : Parcelable

@AndroidEntryPoint
class ChatActivity : BaseActivity<ActivityChatBinding, ChatViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_chat
    override val vm: ChatViewModel by viewModels()

    private var bundle: ChatBundle? = null
    private val messageAdapter = MessageAdapter()

    companion object {
        fun callingIntent(context: Context, bundle: ChatBundle): Intent {
            return Intent(context, ChatActivity::class.java).apply {
                putExtra("bundle", bundle)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        bundle = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("bundle", ChatBundle::class.java)
        } else {
            intent.getParcelableExtra<ChatBundle>("bundle")
        }
    }

    override fun initBinding(): Unit = with(binding) {
        rvMessage.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(this@ChatActivity)
        }
    }

    override fun initListener(): Unit = with(binding) {
        ivClose.setOnClickListener { onBackPressed() }
        ivSend.setOnClickListener {
            vm.sendMessage(etMessage.text.toString())
            etMessage.setText("")
        }
        etMessage.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEND) {
                vm.sendMessage(etMessage.text.toString())
                etMessage.setText("")
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun initObserve(): Unit = with(vm) {
        observe(chat) { chat ->
            binding.tvName.text = chat.user.name
            binding.tvInfo.text = chat.user.getInfo()
        }

        observe(messages) { messages ->
            val group = messages.groupBy { it.date }
            val list = arrayListOf<MessageType>()
            group.map {
                list.add(MessageType.Divider(it.key.getDateString()))
                list.addAll(
                    it.value.map { message ->
                        if (message.isCurrentUser) {
                            MessageType.Send(message)
                        } else {
                            MessageType.Receive(message)
                        }
                    }
                )
            }
            messageAdapter.update(list)
            binding.rvMessage.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    override fun afterBinding() {
        bundle?.let {
            vm.getChats(it.id)
        }
    }
}