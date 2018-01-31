package com.example.yueuy.dream.net.api;

import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.data.user.UserId;
import com.example.yueuy.dream.data.user.UserStoryData;

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

public interface UserApi {

    @POST("/api/signup/")
    Call<UserId> signup(@Body User user);

    @POST("/api/signin/")
    Call<UserId> signin(@Body User user);

    @GET("/api/user/{uid}/")
    Call<UserData> showMyData(@QueryMap Map<String,Integer> map,
                              @Header("auth") String auth);

    @GET("/api/user/{uid}/join/")
    Call<UserStoryData> showStory(@QueryMap Map<String,Object> map,
                                  @Header("auth")String auth);

    @GET("/api/user/{uid}/write/")
    Call<UserStoryData> showWrite(@QueryMap Map<String,Object> map,
                         @Header("auth")String auth);

}
