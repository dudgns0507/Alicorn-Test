package com.github.dudgns0507.alicorn.domain.model

import java.util.Calendar
import kotlin.random.Random

data class MessageData(
    val id: Int,
    val message: String,
    val date: String,
    val time: String,
    val isRead: Boolean,
    val user: UserData
) {
    companion object {
        var id = 0
        private val messageList = listOf("안녕하세요.", "반갑습니다.", "안녕하세요. 박영훈입니다.")

        fun getSampleData(user: UserData): MessageData {
            val date = Calendar.getInstance().get(Calendar.DATE)
            id += 1
            return MessageData(
                id = id,
                message = messageList[Random.nextInt(messageList.size)],
                date = "2022/12/${String.format("%02d", date - Random.nextInt(7))}",
                time = "12:49",
                isRead = false,
                user = user
            )
        }
    }
}