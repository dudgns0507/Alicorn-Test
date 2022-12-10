package com.github.dudgns0507.alicorn.presentation.view.chat

import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.core.BaseViewModel
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.usecase.GetChatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatUseCase: GetChatUseCase
) : BaseViewModel() {
    private val _chat = MutableStateFlow<ChatData?>(null)
    val chat: StateFlow<ChatData?> = _chat

    fun getChats(id: Int) = viewModelScope.launch {
        getChatUseCase(id).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    loading(false)
                    _chat.value = result.data
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
}