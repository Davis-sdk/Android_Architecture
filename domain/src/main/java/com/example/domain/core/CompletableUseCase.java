package com.example.domain.core;

import io.reactivex.Completable;

public interface CompletableUseCase {

    Completable execute();
}
