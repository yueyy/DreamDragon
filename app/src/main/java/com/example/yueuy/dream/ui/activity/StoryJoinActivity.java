package com.example.yueuy.dream.ui.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.UserStartAdapter;
import com.example.yueuy.dream.data.user.UserJoin;
import com.example.yueuy.dream.data.user.UserStart;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-2-5.
 */

public class StoryJoinActivity extends AppCompatActivity {
    private static final String TAG = "StoryStartActivity";
    private Toolbar mToolbar;
    private RecyclerView storyList;
    private SharedPreferencesUtils mPreferencesUtils;
    private int uid;
    private String token;
    private UserJoin mUserJoin;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        initView();
        initData();
    }


    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        storyList = (RecyclerView)findViewById(R.id.story_list_recycle);

        setToolbar(mToolbar);

    }

    private void setToolbar(Toolbar toolbar){


    }


    private void initData(){
        uid = mPreferencesUtils.getUserId("uid");
        token = mPreferencesUtils.getUser("token");
        UserService userService = ServiceGenerator.createService(UserService.class);
        userService.showStory(uid,token).enqueue(new Callback<UserJoin>() {
            @Override
            public void onResponse(Call<UserJoin> call, Response<UserJoin> response) {
                mUserJoin = response.body();

            }

            @Override
            public void onFailure(Call<UserJoin> call, Throwable t) {

            }
        });
    }
}
