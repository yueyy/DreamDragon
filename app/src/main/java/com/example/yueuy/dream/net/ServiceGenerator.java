package com.example.yueuy.dream.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yueuy on 18-1-30.
 */

public class ServiceGenerator {
    public static String apiBaseUrl = "";

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiBaseUrl);

    private static OkHttpClient.Builder client = new OkHttpClient.Builder();

    public static void changeApiBaseUrl(String newApiBaseUrl){
        apiBaseUrl = newApiBaseUrl;

        sBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(apiBaseUrl);
    }
}
