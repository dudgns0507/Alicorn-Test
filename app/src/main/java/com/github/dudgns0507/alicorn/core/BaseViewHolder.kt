package com.github.dudgns0507.alicorn.core

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B : ViewDataBinding>(
    private val binding: B,
    private val listener: ItemClickListener? = null
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            listener?.onClick(adapterPosition)
        }
    }

    protected val ctx: Context
        get() = binding.root.context
}