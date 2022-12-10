package com.github.dudgns0507.alicorn.domain.model

import kotlin.random.Random

data  class ChatData(
    val id: Int,
    val user: UserData,
    val lastMessage: MessageData,
    val unreadMessageCount: Int
) {
    companion object {
        var id = 0

        fun getSampleData(): ChatData {
            id += 1
            val user = UserData.getSampleData()
            return ChatData(
                id = id,
                user = user,
                lastMessage = MessageData.getSampleData(user),
                unreadMessageCount = Random.nextInt(2)
            )
        }
    }
}