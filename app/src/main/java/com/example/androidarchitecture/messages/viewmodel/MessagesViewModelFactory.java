package com.example.androidarchitecture.messages.viewmodel;

import com.example.domain.messages.usecase.GetMessagesUseCase;
import com.example.domain.messages.usecase.SendMessageUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.Factory;


@SuppressWarnings("UNCHECKED_CAST")
@Singleton
public class MessagesViewModelFactory implements ViewModelProvider.Factory {

    private GetMessagesUseCase getMessages;
    private SendMessageUseCase sendMessage;

    @Inject
    public MessagesViewModelFactory(GetMessagesUseCase getMessages, SendMessageUseCase sendMessage)
    {
        this.getMessages = getMessages;
        this.sendMessage = sendMessage;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass)
    {
        if(modelClass.isAssignableFrom(MessagesViewModel.class))
        {
            return (T) new MessagesViewModel(getMessages, sendMessage);
        }
        throw new IllegalArgumentException("Unknow ViewModel class");
    }



}
