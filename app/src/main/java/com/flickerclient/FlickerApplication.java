package com.flickerclient;

import android.app.Application;

import com.flickerclient.data.ServiceLocator;


public class FlickerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceLocator.initServiceLocator("https://api.flickr.com/", getApplicationContext());

    }
}
