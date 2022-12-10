package com.github.dudgns0507.alicorn.di

import com.github.dudgns0507.alicorn.data.repository.MockChatRepositoryImpl
import com.github.dudgns0507.alicorn.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideManagerRepository(
        dispatcher: CoroutineDispatcher
    ): ChatRepository {
        return MockChatRepositoryImpl(dispatcher)
    }
}