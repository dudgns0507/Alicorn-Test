package com.github.dudgns0507.alicorn.presentation.view.main

import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.alicorn.core.ApiResult
import com.github.dudgns0507.alicorn.core.BaseViewModel
import com.github.dudgns0507.alicorn.domain.model.ChatData
import com.github.dudgns0507.alicorn.domain.usecase.GetChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase
) : BaseViewModel() {
    private val _chats = MutableStateFlow(listOf<ChatData>())
    val chats: StateFlow<List<ChatData>> = _chats

    fun getChats() = viewModelScope.launch {
        getChatsUseCase().collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    loading(false)
                    _chats.value = result.data
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