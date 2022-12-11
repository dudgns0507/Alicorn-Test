package com.github.dudgns0507.alicorn.presentation.holder

import com.github.dudgns0507.alicorn.core.BaseViewHolder
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderDividerBinding
import com.github.dudgns0507.alicorn.presentation.adapter.MessageType

class DividerViewHolder(
    private val binding: HolderDividerBinding,
    private val listener: ItemClickListener? = null
) : BaseViewHolder<HolderDividerBinding>(binding, listener) {
    fun bind(position: Int, item: MessageType.Divider) = with(binding) {
        tvDate.text = item.date
    }
}