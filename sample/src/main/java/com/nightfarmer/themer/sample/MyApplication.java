package com.nightfarmer.themer.sample;

import android.app.Application;

import com.nightfarmer.themer.Themer;

/**
 * Created by zhangfan on 16-9-8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Themer.INSTANCE.init(this, 0);
    }
}
