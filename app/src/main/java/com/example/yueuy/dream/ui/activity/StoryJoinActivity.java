package com.example.yueuy.dream.ui.activity;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.UserJoinAdapter;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yueuy on 18-2-5.
 */

public class StoryJoinActivity extends AppCompatActivity {
    private static final String TAG = "StoryStartActivity";
    private Toolbar mToolbar;
    private RecyclerView storyList;
    private LinearLayoutManager mManager;
    private SharedPreferencesUtils mPreferencesUtils;
    private int uid;
    private String token;
    private List<UserJoin.JoinBean> mUserJoin;
    private UserJoinAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        mPreferencesUtils = new SharedPreferencesUtils();
        uid = mPreferencesUtils.getUserId("uid");
        token = mPreferencesUtils.getUser("token");
        initView();
        initData(uid);
    }


    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        storyList = (RecyclerView)findViewById(R.id.story_list_recycle);
        setSupportActionBar(mToolbar);
        setToolbar(mToolbar);
        mManager = new LinearLayoutManager(this);

    }

    private void setToolbar(Toolbar toolbar){

    }


    private void initData(int uid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://120.78.194.125:2000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        service.showStory(uid,token).enqueue(new Callback<UserJoin>() {
            @Override
            public void onResponse(Call<UserJoin> call, Response<UserJoin> response) {
                UserJoin join = response.body();
                mUserJoin = join.getJoin();
                mAdapter = new UserJoinAdapter(getBaseContext(),mUserJoin);
                storyList.setLayoutManager(mManager);
                storyList.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<UserJoin> call, Throwable t) {

            }
        });
    }
}
