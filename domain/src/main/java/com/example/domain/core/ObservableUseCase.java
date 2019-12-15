package com.example.domain.core;

import io.reactivex.Observable;

public interface ObservableUseCase<T> {

    Observable<T> execute();
}