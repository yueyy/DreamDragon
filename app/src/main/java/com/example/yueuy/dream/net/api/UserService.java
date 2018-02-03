package com.example.yueuy.dream.net.api;

import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserAuth;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.data.user.UserId;
import com.example.yueuy.dream.data.user.UserJoin;
import com.example.yueuy.dream.data.user.UserStart;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by yueuy on 18-1-30.
 */

public interface UserService {

    @POST("/api/signup/")
    Call<UserId> signup(@Body User user);

    @POST("/api/signin/")
    Call<UserAuth> login(@Body User user);

    @GET("/api/user/{uid}/")
    Call<UserData> showMyData(@Header("token") String token);

    @GET("/api/user/{uid}/join/")
    Call<UserJoin> showStory(@Header("token")String token);

    @GET("/api/user/{uid}/write/")
    Call<UserStart> showWrite(@Header("token")String token);

}
