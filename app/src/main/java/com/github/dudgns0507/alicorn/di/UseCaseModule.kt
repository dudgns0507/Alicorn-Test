package com.github.dudgns0507.alicorn.di

import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import com.github.dudgns0507.alicorn.domain.usecase.GetChatUseCase
import com.github.dudgns0507.alicorn.domain.usecase.GetChatsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetChatsUseCase(
        repository: ChatRepository
    ): GetChatsUseCase {
        return GetChatsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetChatUseCase(
        repository: ChatRepository
    ): GetChatUseCase {
        return GetChatUseCase(repository)
    }
}