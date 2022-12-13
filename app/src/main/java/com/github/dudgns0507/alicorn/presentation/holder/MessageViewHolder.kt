package com.github.dudgns0507.alicorn.presentation.holder

import android.view.View
import com.github.dudgns0507.alicorn.core.BaseViewHolder
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderMessageBinding
import com.github.dudgns0507.alicorn.presentation.adapter.MessageType

class MessageViewHolder(
    private val binding: HolderMessageBinding,
    private val listener: ItemClickListener? = null
) : BaseViewHolder<HolderMessageBinding>(binding, listener) {
    fun bind(position: Int, item: MessageType.Receive) = with(binding) {
        tvReceive.text = item.message.message
        tvDate.text = item.message.time
        tvRead.visibility = when(item.message.isRead) {
            true -> View.GONE
            false -> View.VISIBLE
        }
    }
}