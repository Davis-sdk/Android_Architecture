package com.example.androidarchitecture.core;


import com.example.androidarchitecture.auth.viewmodel.AuthViewModelFactory;
import com.example.androidarchitecture.messages.viewmodel.MessagesViewModelFactory;
import com.example.data.messages.MessagesRepositoryImpl;
import com.example.data.user.AuthRepositoryImpl;
import com.example.domain.messages.usecase.GetMessagesUseCase;
import com.example.domain.messages.usecase.SendMessageUseCase;
import com.example.domain.user.usecase.LoginUseCase;
import com.example.domain.user.usecase.SignUpUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {

    @Provides
    public AuthViewModelFactory providesAuthViewModelFactory(AuthRepositoryImpl repository)
    {
        return new AuthViewModelFactory(

                        new SignUpUseCase(repository),
                        new LoginUseCase(repository));

    }



        @Provides
        public MessagesViewModelFactory providesViewModelFactory(MessagesRepositoryImpl repository)
        {
            return new MessagesViewModelFactory(
                    new GetMessagesUseCase(repository)
                    , new SendMessageUseCase(repository)
            );
        }



}
