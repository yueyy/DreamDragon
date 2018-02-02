package com.example.yueuy.dream.net.api;

import com.example.yueuy.dream.data.story.NewStoryData;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.StoryRank;
import com.example.yueuy.dream.data.story.Storyc;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yueuy on 18-1-30.
 */

public interface StoryApi {

    @GET("/api/story/rank/")
    Call<StoryRank> showRank(@QueryMap Map<String,Object> map);

    @GET("/api/story/random")
    Call<StoryRandom> showRandom(@QueryMap Map<String,Object> map);

    @GET("/api/story/{storyid}/")
    Call<StoryData> showStory(@Path("storyid") int storyid,
                              @QueryMap Map<String,Object> map);

    @GET("/api/story/{storyid}/like/")
    Call<StoryLike> showLikeNum(@Path("storyid") int storyid,
                                @Header("auth") String auth);

    @POST("/api/story/write/")
    Call<NewStoryData> newStory(@Body StoryData storyData);

    @POST("/api/story/{storyid}/continue/")
    Call<Storyc> continueStory(@Body Storyc storyc,
                               @Query("storycid") int storycid);

    @DELETE("/api/story/{storyid}/delete")
    Call<StoryData> deleteStory(@Query("storyid")int storyid,
                                @Header("auth") String auth);

}
