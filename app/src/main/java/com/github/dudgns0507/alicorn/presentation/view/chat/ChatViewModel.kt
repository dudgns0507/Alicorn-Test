package com.github.dudgns0507.alicorn.presentation.view.chat

import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.alicorn.Constant
import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.core.BaseViewModel
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.model.MessageData
import com.github.dudgns0507.alicorn.domain.model.UserData
import com.github.dudgns0507.alicorn.domain.usecase.GetChatUseCase
import com.github.dudgns0507.alicorn.domain.usecase.ReceiveMessageUseCase
import com.github.dudgns0507.alicorn.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase,
    private val receiveMessageUseCase: ReceiveMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : BaseViewModel() {
    private val _chat = MutableStateFlow(
        ChatData(
            id = 0,
            user = UserData(0, "", "", "", "", ""),
            messages = listOf(),
            unreadMessageCount = 0
        )
    )
    val chat: StateFlow<ChatData> = _chat
    private val _messages = MutableStateFlow<List<MessageData>>(listOf())
    val messages: StateFlow<List<MessageData>> = _messages

    fun getChats(id: Int) = viewModelScope.launch {
        getChatUseCase(id).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    loading(false)
                    _chat.value = result.data
                    _messages.value = result.data.messages
                    receiveMessage(result.data)
                }

                is ApiResult.Failure -> {
                    loading(false)
                }

                is ApiResult.Loading -> {
                    loading(true)
                }
            }
        }
    }

    private fun receiveMessage(chat: ChatData) = viewModelScope.launch {
        receiveMessageUseCase(chat).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    _messages.value = _messages.value + result.data
                }

                is ApiResult.Failure -> {}

                is ApiResult.Loading -> {}
            }
        }
    }

    fun sendMessage(message: String) = viewModelScope.launch {
        val date = Calendar.getInstance()
        val data = MessageData(
            message = message,
            date = SimpleDateFormat("yyyy/MM/dd").format(date.time),
            time = SimpleDateFormat("HH:mm").format(date.time),
            isRead = false,
            user = Constant.currentUser,
            isCurrentUser = true
        )
        sendMessageUseCase(_chat.value, data).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    _messages.value = _messages.value + result.data
                }

                is ApiResult.Failure -> {}

                is ApiResult.Loading -> {}
            }
        }
    }
}