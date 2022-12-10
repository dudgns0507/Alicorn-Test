package com.github.dudgns0507.alicorn.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderChatBinding
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.presentation.holder.ChatViewHolder

class ChatAdapter : RecyclerView.Adapter<ChatViewHolder>() {
    private var items: List<ChatData> = listOf()
    var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            HolderChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(position, items[position])
    }

    fun update(list: List<ChatData>) {
        items = list
        notifyItemRangeChanged(0, itemCount)
    }

    fun getItem(position: Int): ChatData {
        return items[position]
    }
}