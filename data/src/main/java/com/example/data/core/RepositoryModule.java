package com.example.data.core;

import com.example.data.messages.MessagesRepositoryImpl;
import com.example.data.user.AuthRepositoryImpl;
import com.example.domain.messages.repository.MessagesRepository;
import com.example.domain.user.repository.AuthRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public AuthRepository providesAuthRepository(AuthRepositoryImpl repository) {
        return repository;
    }

    @Provides
    public MessagesRepository providesMessagesRepository(MessagesRepositoryImpl repository) {
        return repository;
    }
}
