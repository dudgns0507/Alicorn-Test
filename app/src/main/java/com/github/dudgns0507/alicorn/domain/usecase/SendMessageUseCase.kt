package com.github.dudgns0507.alicorn.domain.usecase

import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.model.MessageData
import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow

class SendMessageUseCase(private val repository: ChatRepository) {
    suspend operator fun invoke(chat: ChatData, message: MessageData): Flow<ApiResult<MessageData>> {
        return repository.sendMessage(chat, message)
    }
}