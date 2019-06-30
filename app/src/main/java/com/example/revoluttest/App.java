package com.example.revoluttest;

import android.app.Application;

import com.blongho.country_data.World;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        World.init(this);
    }
}
