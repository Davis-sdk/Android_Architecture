package com.example.androidarchitecture.auth.viewmodel;

import com.example.domain.user.model.User;
import com.example.domain.user.usecase.LoginUseCase;
import com.example.domain.user.usecase.SignUpUseCase;

import androidx.lifecycle.ViewModel;
import io.reactivex.Single;

public class AuthViewModel extends ViewModel {


    private SignUpUseCase signUp;
    private LoginUseCase login;


    public AuthViewModel(SignUpUseCase signUp, LoginUseCase login)
    {
        this.signUp = signUp;
        this.login = login;
    }


    public Single<User> signUp(String username, String password)
    {
        return signUp.execute(new User(username, password));
    }

    public Single<User> login(String username, String password)
    {
        return login.execute(new User(username, password));
    }
}
