package com.example.domain.user.usecase;

import com.example.domain.core.SingleWithParamUseCase;
import com.example.domain.user.model.User;
import com.example.domain.user.repository.AuthRepository;

import io.reactivex.Single;

public class SignUpUseCase implements SingleWithParamUseCase<User, User> {

    private AuthRepository repository;

    public SignUpUseCase(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<User> execute(User user) {
        return repository.signup(user.getUsername(), user.getPassword());
    }
}
