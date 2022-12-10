package com.github.dudgns0507.alicorn.data.repository

import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.model.UserData
import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MockChatRepositoryImpl(
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {
    private val currentUser = UserData.getSampleData()
    private var chats = listOf(
        ChatData.getSampleData(),
        ChatData.getSampleData(),
        ChatData.getSampleData(),
        ChatData.getSampleData()
    ).sortedByDescending { it.getLastMessage().date }

    private fun checkCurrentUserMessage() {
        chats = chats.map {
            it.copy(messages = it.messages.map { message ->
                message.copy(isCurrentUser = it.user.id == currentUser.id)
            })
        }
    }

    override suspend fun requestChats(): Flow<ApiResult<List<ChatData>>> = withContext(dispatcher) {
        return@withContext flow {
            emit(ApiResult.Loading())
            checkCurrentUserMessage()

            delay(1000L)

            emit(ApiResult.Success(chats))
        }
    }

    override suspend fun requestChat(id: Int): Flow<ApiResult<ChatData>> = withContext(dispatcher) {
        return@withContext flow {
            emit(ApiResult.Loading())
            checkCurrentUserMessage()

            delay(1000L)

            val chat = chats.find { it.id == id }
            chat?.let {
                emit(ApiResult.Success(it))
            } ?: kotlin.run {
                emit(ApiResult.Failure(Exception("Chat data not found.")))
            }
        }
    }
}