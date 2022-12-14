package com.github.dudgns0507.alicorn.domain.repository

import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.model.MessageData
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun requestChats(): Flow<ApiResult<List<ChatData>>>

    suspend fun requestChat(id: Int): Flow<ApiResult<ChatData>>

    suspend fun receiveMessage(chat: ChatData): Flow<ApiResult<MessageData>>

    suspend fun sendMessage(chat: ChatData, message: MessageData): Flow<ApiResult<MessageData>>
}