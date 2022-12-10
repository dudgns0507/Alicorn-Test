package com.github.dudgns0507.alicorn.presentation.holder

import com.github.dudgns0507.alicorn.core.BaseViewHolder
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderMessageBinding
import com.github.dudgns0507.alicorn.domain.model.MessageData

class MessageViewHolder(
    private val binding: HolderMessageBinding,
    private val listener: ItemClickListener? = null
) : BaseViewHolder<HolderMessageBinding>(binding, listener) {
    fun bind(position: Int, item: MessageData) = with(binding) {
    }
}