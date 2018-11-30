package com.example.recytechco;

import android.app.Application;
import android.content.Intent;

import com.example.recytechco.util.Config;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Config.getUserId(this) == -1) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
