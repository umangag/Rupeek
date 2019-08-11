package com.umang.myapplication;

import android.app.Application;

/**
 * Created by umangagarwal on 11,August,2019
 */
public class ApplicationClass extends Application {

    static ApplicationClass applicationClass;


    public static ApplicationClass getApp() {
        return applicationClass;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationClass.applicationClass = this;
    }
}
