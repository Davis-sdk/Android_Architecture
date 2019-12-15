package com.example.domain.user.repository;

import com.example.domain.user.model.User;

import io.reactivex.Single;

public interface AuthRepository {

    Single<User> signup(String username, String password);

    Single<User> login(String username, String password);
}
