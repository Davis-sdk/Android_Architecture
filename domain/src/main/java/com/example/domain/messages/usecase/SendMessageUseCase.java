package com.example.domain.messages.usecase;


import com.example.domain.core.CompletableWithParamUseCase;
import com.example.domain.messages.model.Message;
import com.example.domain.messages.repository.MessagesRepository;

import io.reactivex.Completable;

public class SendMessageUseCase implements CompletableWithParamUseCase<Message> {

    private MessagesRepository repository;

    public SendMessageUseCase(MessagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Completable execute(Message message) {
        return repository.sendMessage(message);
    }
}
