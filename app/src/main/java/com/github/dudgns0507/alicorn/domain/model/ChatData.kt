package com.github.dudgns0507.alicorn.domain.model

import kotlin.random.Random

data class ChatData(
    val id: Int,
    val user: UserData,
    val messages: List<MessageData>,
    val unreadMessageCount: Int
) {
    fun getLastMessage(): MessageData {
        return messages.last()
    }

    companion object {
        var id = 0

        fun getSampleData(): ChatData {
            id += 1
            val user = UserData.getSampleData()
            val lastMessage = MessageData.getSampleData(user)
            return ChatData(
                id = id,
                user = user,
                messages = listOf(lastMessage),
                unreadMessageCount = Random.nextInt(2)
            )
        }
    }
}