package com.github.dudgns0507.alicorn.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderMessageBinding
import com.github.dudgns0507.alicorn.domain.model.MessageData
import com.github.dudgns0507.alicorn.presentation.holder.MessageViewHolder

class MessageAdapter : RecyclerView.Adapter<MessageViewHolder>() {
    private var items: List<MessageData> = listOf()
    var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            HolderMessageBinding.inflate(
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

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(position, items[position])
    }

    fun update(list: List<MessageData>) {
        items = list
        notifyItemRangeChanged(0, itemCount)
    }

    fun getItem(position: Int): MessageData {
        return items[position]
    }
}