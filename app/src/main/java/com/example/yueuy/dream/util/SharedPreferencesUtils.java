package com.example.yueuy.dream.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by yueuy on 18-2-1.
 */

public class SharedPreferencesUtils {
    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    private static void throwTrouble(){
        if (mContext == null){
            throw new NullPointerException("error");
        }
    }

    public static void set(String name,String key,String value){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String get(String name,String key,String value){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(name,Context.MODE_PRIVATE);

        return sp.getString(key,value);
    }

    public static void set(String name,String key,int value) {
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

}
