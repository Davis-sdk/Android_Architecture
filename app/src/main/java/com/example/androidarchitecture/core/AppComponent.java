package com.example.androidarchitecture.core;


import com.example.androidarchitecture.auth.viewmodel.AuthViewModelFactory;
import com.example.androidarchitecture.messages.viewmodel.MessagesViewModelFactory;
import com.example.data.core.DatabaseModule;
import com.example.data.core.RepositoryModule;
import com.example.domain.messages.repository.MessagesRepository;
import com.example.domain.user.repository.AuthRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import dagger.Component;

@Component(modules =
        {
                ViewModelModule.class,
                DatabaseModule.class,
                RepositoryModule.class
        })

public interface AppComponent {

    AuthRepository authRepository();

    MessagesRepository messagesRepository();

    AuthViewModelFactory authViewModelFactory();

    MessagesViewModelFactory messagesViewModelFactory();

    FirebaseFirestore firebaseFirestore();








}
