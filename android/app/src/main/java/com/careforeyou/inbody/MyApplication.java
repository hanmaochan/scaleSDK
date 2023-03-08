package com.careforeyou.inbody;

import android.app.Application;
import android.content.Context;


import com.careforeyou.library.utils.LogUtil;


public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


    }
    public static Context getContexts() {
        return context;
    }
}
