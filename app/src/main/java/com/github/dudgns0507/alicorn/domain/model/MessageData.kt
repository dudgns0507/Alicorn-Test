package com.github.dudgns0507.alicorn.domain.model

import java.util.Calendar
import kotlin.random.Random

data class MessageData(
    val message: String,
    val date: String,
    val time: String,
    val isRead: Boolean,
    val user: UserData,
    val isCurrentUser: Boolean = false
) {
    companion object {
        private val messageList = listOf(
            "안녕하세요.",
            "반갑습니다.",
            "메시지 테스트.",
            "URL 테스트 https://www.naver.com/",
            "URL https://www.naver.com/ 테스트 입니다."
        )

        fun getSampleData(user: UserData): MessageData {
            val date = Calendar.getInstance().get(Calendar.DATE)
            return MessageData(
                message = messageList[Random.nextInt(messageList.size)],
                date = "2022/12/${String.format("%02d", date - Random.nextInt(7))}",
                time = "12:49",
                isRead = false,
                user = user
            )
        }

        fun getRandomMessage(): String {
            return messageList[Random.nextInt(messageList.size)]
        }
    }
}