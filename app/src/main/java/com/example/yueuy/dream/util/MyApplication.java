package com.example.yueuy.dream.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by yueuy on 18-2-4.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static Context getInstance(){
        if (mInstance == null){
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
