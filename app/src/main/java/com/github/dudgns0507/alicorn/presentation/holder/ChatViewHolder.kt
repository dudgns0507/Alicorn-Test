package com.github.dudgns0507.alicorn.presentation.holder

import com.bumptech.glide.Glide
import com.github.dudgns0507.alicorn.core.BaseViewHolder
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.core.getDateString
import com.github.dudgns0507.alicorn.databinding.HolderChatBinding
import com.github.dudgns0507.alicorn.domain.model.ChatData

class ChatViewHolder(
    private val binding: HolderChatBinding,
    private val listener: ItemClickListener? = null
) : BaseViewHolder<HolderChatBinding>(binding, listener) {
    fun bind(position: Int, item: ChatData) = with(binding) {
        ivProfile.clipToOutline = true
        if (item.user.profile.contains("http"))
            Glide.with(ctx).load(item.user.profile).into(ivProfile)

        tvName.text = item.user.name
        tvInfo.text = item.user.getInfo()
        tvMessage.text = item.getLastMessage().message
        tvDate.text = item.getLastMessage().date.getDateString()
    }
}