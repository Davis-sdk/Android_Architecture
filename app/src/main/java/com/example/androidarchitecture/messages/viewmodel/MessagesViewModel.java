package com.example.androidarchitecture.messages.viewmodel;

import com.example.domain.messages.model.Message;
import com.example.domain.messages.usecase.GetMessagesUseCase;
import com.example.domain.messages.usecase.SendMessageUseCase;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class MessagesViewModel extends ViewModel{

    private GetMessagesUseCase getMessages;
    private SendMessageUseCase sendMessage;

    public MessagesViewModel(GetMessagesUseCase getMessages, SendMessageUseCase sendMessage)
    {
        this.getMessages =getMessages;
        this.sendMessage = sendMessage;
    }


    public Completable sendMessages(Message message){return sendMessage.execute(message); }

    public Observable<List<Message>> getMessages(){return getMessages.execute();}

}
