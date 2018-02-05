package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.StoryRecycleAdapter;
import com.example.yueuy.dream.adapter.UserStartAdapter;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.Storyc;
import com.example.yueuy.dream.data.story.StorycId;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserStart;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-2-2.
 */

public class StoryStartActivity extends AppCompatActivity {

    private static final String TAG = "StoryStartActivity";
    private Toolbar mToolbar;
    private RecyclerView storyList;
    private int uid;
    private String token;
    private List<UserStart.Start> mUserStarts;
    private UserStartAdapter mStartAdapter;
    private SharedPreferencesUtils mPreferencesUtils;
    private LinearLayoutManager mManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initView();
        mPreferencesUtils = new SharedPreferencesUtils();
        uid = mPreferencesUtils.getUserId("uid");
        token = mPreferencesUtils.getUser("token");
        initData(uid);
    }


    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        storyList = (RecyclerView)findViewById(R.id.story_list_recycle);
        setSupportActionBar(mToolbar);
        mManager = new LinearLayoutManager(this);
//        mToolbar.setTitle("我发起的故事");
    }
    private void initData(int uid){
        UserService userService = ServiceGenerator.createService(UserService.class);
        userService.showWrite(uid,token).enqueue(new Callback<UserStart.Start>() {
            @Override
            public void onResponse(Call<UserStart.Start> call, Response<UserStart.Start> response) {
                try {
                    UserStart.Start start = response.body();
                    mUserStarts = start.getStart();
                    mStartAdapter = new UserStartAdapter(getBaseContext(), mUserStarts);
                    storyList.setLayoutManager(mManager);
                    storyList.setAdapter(mStartAdapter);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserStart.Start> call, Throwable t) {

            }
        });
    }


}
