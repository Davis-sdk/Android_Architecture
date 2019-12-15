package com.example.androidarchitecture.core;

import android.app.Application;

import com.example.data.core.DatabaseModule;
import com.example.data.core.RepositoryModule;

import dagger.internal.DaggerCollections;

public class App extends Application{


    private static App INSTANCE = null;

    public AppComponent component;


    @Override
    public void onCreate()
    {
        super.onCreate();
        INSTANCE = this;
        component = DaggerAppComponent.builder()
                .databaseModule(new DatabaseModule())
                .repositoryModule(new RepositoryModule())
                .viewModelModule(new ViewModelModule())
                .build();

    }


    public static App get(){return INSTANCE;}





}
