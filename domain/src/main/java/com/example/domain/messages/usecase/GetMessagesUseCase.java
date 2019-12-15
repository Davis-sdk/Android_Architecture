package com.example.domain.messages.usecase;

import com.example.domain.core.ObservableUseCase;
import com.example.domain.messages.model.Message;
import com.example.domain.messages.repository.MessagesRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetMessagesUseCase implements ObservableUseCase<List<Message>> {

    private MessagesRepository repository;

    public GetMessagesUseCase(MessagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<Message>> execute() {
        return repository.getMessages();
    }
}
