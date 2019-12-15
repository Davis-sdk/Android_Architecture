package com.example.androidarchitecture.core;

import javax.inject.Inject;

public class Injector {


private Injector()
{

}


    public static AppComponent get() {return App.get().component;}

}
