package com.github.dudgns0507.alicorn.domain.model

import kotlin.random.Random

data class UserData(
    val id: Int,
    val email: String,
    val name: String,
    val job: String,
    val company: String,
    val profile: String
) {
    fun getInfo(): String {
        return "$job @${company}"
    }

    companion object {
        var id = 0
        private val nameList = listOf("박영훈", "홍길동")
        private val emailList = listOf("hoon2585@gmail.com", "abc@gmail.com", "alicorn@gmail.com")
        private val jobList = listOf("프론트엔드 개발자", "UX 디자이너", "백엔드 개발자", "안드로이드 개발자")

        fun getSampleData(): UserData {
            id += 1
            return UserData(
                id = id,
                email = emailList[Random.nextInt(emailList.size)],
                name = nameList[Random.nextInt(nameList.size)],
                job = jobList[Random.nextInt(jobList.size)],
                company = "로켓펀치",
                profile = ""
            )
        }
    }
}