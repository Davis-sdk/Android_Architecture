package com.example.domain.core;

import io.reactivex.Completable;

public interface CompletableWithParamUseCase<T> {

    Completable execute(T t);
}
