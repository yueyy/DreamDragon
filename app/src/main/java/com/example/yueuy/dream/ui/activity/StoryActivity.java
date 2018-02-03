package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.Storyc;
import com.example.yueuy.dream.data.story.StorycId;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-2-2.
 */

public class StoryActivity extends AppCompatActivity {

    private static final String TAG = "StoryActivity";
    private Toolbar mToolbar;
    private RecyclerView storyList;
    private TextInputEditText edtStoryc;
    private SharedPreferencesUtils mPreferencesUtils;

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
        edtStoryc = (TextInputEditText)findViewById(R.id.edt_story_continue);


    }
    private void initData(){
        Intent i = getIntent();
        Bundle data = i.getExtras();

    }

    private void continuation(){
        String c = edtStoryc.getText().toString();
        Storyc storyc = new Storyc();
        storyc.setStoryc(c);
        storyc.setUid(mPreferencesUtils.getUserId("uid"));
        String token = mPreferencesUtils.getUser("token");
        StoryService storyService = ServiceGenerator.createService(StoryService.class);
        storyService.continuation(storyc,token).enqueue(new Callback<StorycId>() {
            @Override
            public void onResponse(Call<StorycId> call, Response<StorycId> response) {
                if (response.isSuccessful()) {
//                    int storycid = response.body().getStorycid();
//                    mPreferencesUtils.setStory(""+storycid,storycid);
                    Toast.makeText(getBaseContext(),"您的脑洞已发送!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getBaseContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StorycId> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });

    }
}
