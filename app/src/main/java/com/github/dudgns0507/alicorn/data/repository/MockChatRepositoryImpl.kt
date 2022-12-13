package com.github.dudgns0507.alicorn.data.repository

import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.model.MessageData
import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar

class MockChatRepositoryImpl(
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {
    private var chats = listOf(
        ChatData.getSampleData(),
        ChatData.getSampleData(),
        ChatData.getSampleData(),
        ChatData.getSampleData()
    ).sortedByDescending { it.getLastMessage().date }

    override suspend fun requestChats(): Flow<ApiResult<List<ChatData>>> = withContext(dispatcher) {
        return@withContext flow {
            emit(ApiResult.Loading())

            delay(1000L)

            emit(ApiResult.Success(chats))
        }
    }

    override suspend fun requestChat(id: Int): Flow<ApiResult<ChatData>> = withContext(dispatcher) {
        return@withContext flow {
            emit(ApiResult.Loading())

            delay(1000L)

            chats = chats.map { chat ->
                if (chat.id == id)
                    chat.copy(messages = chat.messages.map { message ->
                        message.copy(
                            isRead = true
                        )
                    })
                else
                    chat
            }
            val chat = chats.find { it.id == id }
            chat?.let {
                emit(ApiResult.Success(it))
            } ?: kotlin.run {
                emit(ApiResult.Failure(Exception("Chat data not found.")))
            }
        }
    }

    override suspend fun receiveMessage(
        chat: ChatData
    ): Flow<ApiResult<MessageData>> = withContext(dispatcher) {
        return@withContext flow {
            for (i in 0..50) {
                delay(5000L)
                val date = Calendar.getInstance()
                val data = MessageData(
                    message = MessageData.getRandomMessage(),
                    date = SimpleDateFormat("yyyy/MM/dd").format(date.time),
                    time = SimpleDateFormat("HH:mm").format(date.time),
                    isRead = true,
                    user = chat.user,
                    isCurrentUser = false
                )

                chats = chats.map {
                    if (it.id == chat.id)
                        it.copy(messages = it.messages.map { message ->
                            message.copy(isRead = true)
                        } + data)
                    else
                        it
                }
                emit(ApiResult.Success(data))
            }
        }
    }

    override suspend fun sendMessage(
        chat: ChatData, message: MessageData
    ): Flow<ApiResult<MessageData>> = withContext(dispatcher) {
        return@withContext flow {
            chats = chats.map {
                if (it.id == chat.id) it.copy(messages = it.messages + message)
                else it
            }
            emit(ApiResult.Success(message))
        }
    }
}