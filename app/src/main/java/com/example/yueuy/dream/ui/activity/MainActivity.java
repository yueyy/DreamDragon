package com.example.yueuy.dream.ui.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.FragmentAdapter;
import com.example.yueuy.dream.data.Constants;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.StoryRank;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.ui.fragment.FragmentHome;
import com.example.yueuy.dream.ui.fragment.FragmentUser;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import com.muxistudio.cardbanner.CardBanner;
import com.muxistudio.cardbanner.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private static final String TAG = "main";
    private boolean check = true;
    private Toolbar mToolbar;
    private ImageButton btnHome;
    private ImageButton btnNewStory;
    private ImageButton btnUser;
    private CardBanner mBanner;
    private ViewPager mViewPager;
    private FragmentAdapter mFragmentAdapter;
    private SharedPreferencesUtils mPreferencesUtils;
    private UserData mUserData;
    private StoryRandom mRandom;
    private StoryRank mRank;
    private int uid;
    private FragmentHome mFragmentHome;
    private FragmentUser mFragmentUser;
    private List<Fragment> mFragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar_home);
        btnHome = (ImageButton)findViewById(R.id.btn_home_page);
        btnUser = (ImageButton) findViewById(R.id.btn_user_page);
        btnNewStory = (ImageButton) findViewById(R.id.btn_new_story);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);

        btnHome.setOnClickListener(this);
        btnUser.setOnClickListener(this);
        btnNewStory.setOnClickListener(this);

    }

    private void newStory(){
        Intent i = new Intent(MainActivity.this,NewStoryActivity.class);
        startActivity(i);
        finish();
    }

    private void initData(){
        mPreferencesUtils = new SharedPreferencesUtils();
        mPreferencesUtils.init(MainActivity.this);
//        String token = mPreferencesUtils.getUser("token");
        mFragmentHome = new FragmentHome();
        mFragmentUser = new FragmentUser();
        mFragmentList.add(mFragmentHome);
        mFragmentList.add(mFragmentUser);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
//        if (token.equals("unknown")){
//            mFragmentHome = new FragmentHome();
//            mFragmentUser = new FragmentUser();
//            mFragmentList.add(mFragmentHome);
//            mFragmentList.add(mFragmentUser);
//            mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
//            mViewPager.setAdapter(mFragmentAdapter);
//        }else {
//            //  登录状态
//            //  获得用户数据
//            mFragmentHome = new FragmentHome();
//            mFragmentUser = new FragmentUser();
//            mFragmentList.add(mFragmentHome);
//            mFragmentList.add(mFragmentUser);
//            mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
//            uid = mPreferencesUtils.getUserId("uid");
//            UserService userService = ServiceGenerator.createService(UserService.class);
//            userService.showMyData(uid,token).enqueue(new Callback<UserData>() {
//                @Override
//                public void onResponse(Call<UserData> call, Response<UserData> response) {
//                    if (response.isSuccessful()) {
//                        mUserData = new UserData();
//                        mUserData = response.body();
//                        mFragmentUser = FragmentUser.newInstance(mUserData);
//                        mFragmentList.add(mFragmentUser);
//                        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
//                        mViewPager.setAdapter(mFragmentAdapter);
//                    } else {
//                        Toast.makeText(getBaseContext(), "main error", Toast.LENGTH_SHORT).show();
//                        Log.i(TAG, "onResponse: userdata");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<UserData> call, Throwable t) {
//                    Toast.makeText(getBaseContext(), "请先登录!", Toast.LENGTH_SHORT).show();
//                }
//            });
        }


    @Override
    public void onPageScrollStateChanged(int arg0){

    }

    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2){

    }

    @Override
    public void onPageSelected(int arg0){

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_home_page:
                mViewPager.setCurrentItem(0);

                break;
            case R.id.btn_user_page:
                mViewPager.setCurrentItem(1);

                break;
            case R.id.btn_new_story:
                newStory();
                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        switch (resultCode){
//
//        }
//    }
}
