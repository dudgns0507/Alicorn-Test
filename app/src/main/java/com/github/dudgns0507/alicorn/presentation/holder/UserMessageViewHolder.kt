package com.github.dudgns0507.alicorn.presentation.holder

import android.view.View
import com.github.dudgns0507.alicorn.core.BaseViewHolder
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderUserMessageBinding
import com.github.dudgns0507.alicorn.presentation.adapter.MessageType

class UserMessageViewHolder(
    private val binding: HolderUserMessageBinding,
    private val listener: ItemClickListener? = null
) : BaseViewHolder<HolderUserMessageBinding>(binding, listener) {
    fun bind(position: Int, item: MessageType.Send) = with(binding) {
        tvSend.text = item.message.message
        tvDate.text = item.message.time
        tvRead.visibility = when(item.message.isRead) {
            true -> View.GONE
            false -> View.VISIBLE
        }
    }
}