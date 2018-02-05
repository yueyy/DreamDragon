package com.example.yueuy.dream.net.api;

import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryId;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.StoryRank;
import com.example.yueuy.dream.data.story.StoryWrite;
import com.example.yueuy.dream.data.story.Storyc;
import com.example.yueuy.dream.data.story.StorycId;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yueuy on 18-1-30.
 */

public interface StoryService {

    //查看排行榜
    @GET("/api/story/rank/")
    Call<StoryRank> showRank();

    //随机故事
    @GET("/api/story/random/")
    Call<StoryRandom> showRandom();

    //查看故事详情
    @GET("/api/story/{storyid}/")
    Call<StoryData> showStory(@Path("storyid") int storyId);

    //点赞
    @GET("/api/story/{storyid}/like/")
    Call<StoryLike> like(@Path("storyid") int storyId,
                         @Header("token") String token);

    //发起故事
    @POST("/api/story/write/")
    Call<StoryId> newStory(@Body StoryWrite storyWrite,
                           @Header("token")String token);

    //续写
    @POST("api/story/{storyid}/continue/")
    Call<StorycId> continuation(@Path("storyid")int storyId,
                                @Body Storyc storyc,
                                @Header("token")String token);

    //删除
    @DELETE("/api/story/{storyid}/delete")
    Call<ResponseBody> deleteStory(@Query("storyid")int storyId,
                                   @Header("token") String token);

}