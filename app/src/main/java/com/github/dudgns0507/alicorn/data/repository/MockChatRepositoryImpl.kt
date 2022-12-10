package com.github.dudgns0507.alicorn.data.repository

import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MockChatRepositoryImpl(
    private val dispatcher: CoroutineDispatcher
) : ChatRepository {
    override suspend fun requestChats(): Flow<ApiResult<List<ChatData>>> = withContext(dispatcher) {
        return@withContext flow {
            emit(ApiResult.Loading())

            val chats = listOf(
                ChatData.getSampleData(),
                ChatData.getSampleData()
            )

            emit(ApiResult.Success(chats))
        }
    }
}