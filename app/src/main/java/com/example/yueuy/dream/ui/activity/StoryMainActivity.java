package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.StoryDataAdapter;
import com.example.yueuy.dream.adapter.StoryKeyAdapter;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.Storyc;
import com.example.yueuy.dream.data.story.StorycId;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import com.example.yueuy.dream.util.SpaceItemDecoration;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-2-3.
 */

public class StoryMainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "StoryMainActivity";
    private Toolbar mToolbar;
    private TextView tvStory;
    private TextView tvLike;
    private TextView tvUsername;
    private RecyclerView mKeyRecycle;
    private RecyclerView mStorycRecycle;
    private StoryData mStoryData;
    private StoryDataAdapter mAdapter;
    private EditText mEditText;
    private Button mSend;
    private ImageButton mLike;
    private StoryKeyAdapter mKeyAdapter;
    private SharedPreferencesUtils mPreferencesUtils;
    private LinearLayoutManager mManager;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        initView();
        initData();
    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        tvStory = (TextView)findViewById(R.id.story_all_content);
        mKeyRecycle = (RecyclerView)findViewById(R.id.story_main_keyword);
        mStorycRecycle = (RecyclerView) findViewById(R.id.story_main_recycle);
        mLike = (ImageButton)findViewById(R.id.story_main_like_img);
        tvUsername = (TextView)findViewById(R.id.story_main_username);
        tvLike = (TextView)findViewById(R.id.story_main_like);
        mEditText = (EditText)findViewById(R.id.story_main_continue);
        mSend = (Button)findViewById(R.id.story_main_send);
        mPreferencesUtils = new SharedPreferencesUtils();
        mManager = new LinearLayoutManager(this);
        mLayoutManager = new LinearLayoutManager(this);
        mLike.setOnClickListener(this);
        mSend.setOnClickListener(this);
    }

    private void initData(){
        Intent intent = getIntent();
        int storyid = intent.getIntExtra("storyid",0);
        if (storyid != 0) {
            StoryService storyService = ServiceGenerator.createService(StoryService.class);
            storyService.showStory(storyid).enqueue(new Callback<StoryData>() {
                @Override
                public void onResponse(Call<StoryData> call, Response<StoryData> response) {
                    mStoryData = response.body();
                    try {
                        tvStory.setText(mStoryData.getStory());
                        tvUsername.setText(mStoryData.getUsername());
                        tvLike.setText(String.valueOf(mStoryData.getLikenum()));
                        //    关键字recycleView
                        mManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        mKeyRecycle.setLayoutManager(mManager);
                        mKeyRecycle.addItemDecoration(new SpaceItemDecoration(5));
                        mKeyAdapter = new StoryKeyAdapter(getBaseContext(),mStoryData.getKeywords());
                        mKeyRecycle.setAdapter(mKeyAdapter);
                        //    续写recycleView
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mStorycRecycle.setLayoutManager(mLayoutManager);
                        mAdapter = new StoryDataAdapter(getBaseContext(), mStoryData.getStoryc());
                        mStorycRecycle.setAdapter(mAdapter);
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<StoryData> call, Throwable t) {

                }
            });
        }else {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

    private void continuation(){
        String c = mEditText.getText().toString();
        Storyc storyc = new Storyc();
        storyc.setStoryc(c);
        String token = mPreferencesUtils.getUser("token");
        if (token.equals("unknown")) {
            Toast.makeText(getBaseContext(),"请先登录",Toast.LENGTH_SHORT).show();
        }else {
            int uid = mPreferencesUtils.getUserId("uid");
            String uId = String.valueOf(uid);
            storyc.setUid(uId);
            StoryService storyService = ServiceGenerator.createService(StoryService.class);
            storyService.continuation(uid,storyc, token).enqueue(new Callback<StorycId>() {
                @Override
                public void onResponse(Call<StorycId> call, Response<StorycId> response) {
                    Log.i(TAG, "onResponse: "+response.code()+response.headers());
                    if (response.isSuccessful()) {
                        Toast.makeText(getBaseContext(), "您的脑洞已发送!", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i(TAG, "onResponse: "+response.body());
                        Toast.makeText(getBaseContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StorycId> call, Throwable t) {
                    Log.i(TAG, "onFailure: ");
                }
            });
        }
    }

    private void like(int storyid){
        String token = mPreferencesUtils.getUser("token");
        if (token!=null||storyid != 0){
            StoryService storyService = ServiceGenerator.createService(StoryService.class);
            storyService.like(storyid,token).enqueue(new Callback<StoryLike>() {
                @Override
                public void onResponse(Call<StoryLike> call, Response<StoryLike> response) {
                    try {
                        mLike.setColorFilter(Color.RED);
                        tvLike.setText(String.valueOf(response.body().getLikenum()));
                        Toast.makeText(getBaseContext(),"点赞成功",Toast.LENGTH_SHORT).show();
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StoryLike> call, Throwable t) {
                    Toast.makeText(getBaseContext(),"点赞失败",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(getBaseContext(),"点赞失败,请先登录",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.story_main_like_img:
                Intent intent = getIntent();
                int storyid = intent.getIntExtra("storyid",0);
                like(storyid);
                break;
            case R.id.story_main_send:
                continuation();
                break;
            default:
                break;
        }
    }

}
