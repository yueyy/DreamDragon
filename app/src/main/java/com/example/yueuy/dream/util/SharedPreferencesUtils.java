package com.example.yueuy.dream.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.example.yueuy.dream.data.Constants;

import java.util.Map;

/**
 * Created by yueuy on 18-2-1.
 */

public class SharedPreferencesUtils {
    private static Context mContext;

    public void init(Context context){
        mContext = context;
    }

    private static void throwTrouble(){
        if (mContext == null){
            throw new NullPointerException("error");
        }
    }

    public void setUser(String key,String value){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public void setUser(String key,int value) {
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getUser(String key){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.USER,Context.MODE_PRIVATE);

        return sp.getString(key,"");
    }

    public int getUserId(String key){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.USER,Context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }

    public void setStory(String key,String value){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.STORY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public void setStory(String key,int value) {
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.STORY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getStory(String key){
        throwTrouble();
        SharedPreferences sp = mContext.getSharedPreferences(Constants.STORY,Context.MODE_PRIVATE);

        return sp.getString(key,"");
    }



}
