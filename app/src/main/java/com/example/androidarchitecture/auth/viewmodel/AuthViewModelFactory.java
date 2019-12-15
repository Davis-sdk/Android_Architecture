package com.example.androidarchitecture.auth.viewmodel;

import com.example.domain.user.usecase.LoginUseCase;
import com.example.domain.user.usecase.SignUpUseCase;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("UNCHECKED_CAST")
@Singleton
public class AuthViewModelFactory implements ViewModelProvider.Factory {

    private SignUpUseCase signUp;
    private LoginUseCase login;


    @Inject
    public AuthViewModelFactory(SignUpUseCase signUp, LoginUseCase login)
    {
        this.signUp = signUp;
        this.login = login;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass)
    {
        if(modelClass.isAssignableFrom(AuthViewModel.class))
        {
            return  (T) new AuthViewModel(signUp, login);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }







}
