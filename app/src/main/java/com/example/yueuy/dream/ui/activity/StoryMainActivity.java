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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.StoryDataAdapter;
import com.example.yueuy.dream.adapter.StoryKeyAdapter;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryId;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.Storyc;
import com.example.yueuy.dream.data.story.StorycId;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import com.example.yueuy.dream.util.SpaceItemDecoration;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        initData(getStoryId());
    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        tvStory = (TextView)findViewById(R.id.story_all_content);
        mKeyRecycle = (RecyclerView)findViewById(R.id.story_main_keyword);
        mStorycRecycle = (RecyclerView) findViewById(R.id.story_continue_recycle);
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

    private void initData(int storyid){
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
                        List<StoryData.KeywordsBean> keywordsBeanList = new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            if (!mStoryData.getKeywords().get(i).toString().equals("")){
                                keywordsBeanList.add(mStoryData.getKeywords().get(i));
                            }
                        }
                        mKeyAdapter = new StoryKeyAdapter(getBaseContext(),keywordsBeanList);
                        mKeyRecycle.setAdapter(mKeyAdapter);
                        //    续写recycleView
                        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mStorycRecycle.setLayoutManager(mLayoutManager);
                        mAdapter = new StoryDataAdapter(getBaseContext(), mStoryData.getStoryc());
                        mStorycRecycle.addItemDecoration(new SpaceItemDecoration(5));
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

    private void continuation(int storyId){
        String c = mEditText.getText().toString();

        String token = mPreferencesUtils.getUser("token");
        if (token.equals("unknown")) {
            Toast.makeText(getBaseContext(),"请先登录",Toast.LENGTH_SHORT).show();
        }else {
            int uid = mPreferencesUtils.getUserId("uid");
            Storyc storyc = new Storyc();
            storyc.setUid(uid);
            storyc.setStoryc(c);

            StoryService service = ServiceGenerator.createService(StoryService.class);
            Call<StorycId> call = service.continuation(storyId,storyc,token);
            call.enqueue(new Callback<StorycId>() {
                @Override
                public void onResponse(Call<StorycId> call, Response<StorycId> response) {
                    int id = response.body().getStorycid();
                    Log.i(TAG, "onResponse: "+response.body()+response.headers());
                    Toast.makeText(getBaseContext(),"你的脑洞发射成功! 代号为: "+id,Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<StorycId> call, Throwable t) {

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

    private int getStoryId(){
        Intent intent = getIntent();
        int storyid = intent.getIntExtra("storyid",0);
        return storyid;
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.story_main_like_img:
                like(getStoryId());
                break;
            case R.id.story_main_send:
                continuation(getStoryId());
                break;
            default:
                break;
        }
    }



}
