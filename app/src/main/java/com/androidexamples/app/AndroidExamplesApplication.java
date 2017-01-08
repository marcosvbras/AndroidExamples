package com.androidexamples.app;

import android.app.Application;

/**
 * Created by marcos on 07/01/2017.
 */

public class AndroidExamplesApplication extends Application {

    // Vincgular essa classe na tag Application do Android Manifest

    public static AndroidExamplesApplication instance;

    public static AndroidExamplesApplication getInstance() {
        return instance; // Singleton
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
