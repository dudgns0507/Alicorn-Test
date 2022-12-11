package com.github.dudgns0507.alicorn.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.alicorn.core.ItemClickListener
import com.github.dudgns0507.alicorn.databinding.HolderDividerBinding
import com.github.dudgns0507.alicorn.databinding.HolderMessageBinding
import com.github.dudgns0507.alicorn.databinding.HolderUserMessageBinding
import com.github.dudgns0507.alicorn.domain.model.MessageData
import com.github.dudgns0507.alicorn.presentation.holder.DividerViewHolder
import com.github.dudgns0507.alicorn.presentation.holder.MessageViewHolder
import com.github.dudgns0507.alicorn.presentation.holder.UserMessageViewHolder

sealed class MessageType {
    data class Receive(
        val message: MessageData
    ) : MessageType()

    data class Send(
        val message: MessageData
    ) : MessageType()

    data class Divider(
        val date: String
    ) : MessageType()
}

enum class MessageTypeEnum(val id: Int) {
    RECEIVE(1), SEND(2), DIVIDER(3)
}

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<MessageType> = listOf()
    var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MessageTypeEnum.RECEIVE.id -> MessageViewHolder(
                HolderMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            MessageTypeEnum.SEND.id -> UserMessageViewHolder(
                HolderUserMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> DividerViewHolder(
                HolderDividerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MessageViewHolder -> holder.bind(position, items[position] as MessageType.Receive)
            is UserMessageViewHolder -> holder.bind(position, items[position] as MessageType.Send)
            is DividerViewHolder -> holder.bind(position, items[position] as MessageType.Divider)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MessageType.Receive -> MessageTypeEnum.RECEIVE.id
            is MessageType.Send -> MessageTypeEnum.SEND.id
            is MessageType.Divider -> MessageTypeEnum.DIVIDER.id
        }
    }

    fun update(list: List<MessageType>) {
        items = list
        notifyItemRangeChanged(0, itemCount)
    }

    fun getItem(position: Int): MessageType {
        return items[position]
    }
}