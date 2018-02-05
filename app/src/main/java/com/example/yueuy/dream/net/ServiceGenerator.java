package com.example.yueuy.dream.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yueuy on 18-1-30.
 */

public class ServiceGenerator {
    public static final String BASE_URL = "http://120.78.194.125:2000/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();

    private static Retrofit.Builder sBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass){
        return createService(serviceClass,null);
    }

    public static <S> S createService(Class<S> serviceClass,final String authToken){
        if (authToken != null){
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("token",authToken)
                            .method(original.method(),original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
//            httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY ));
        }
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = sBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
