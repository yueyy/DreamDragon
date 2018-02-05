package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.FragmentAdapter;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.StoryRank;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.ui.fragment.FragmentHome;
import com.example.yueuy.dream.ui.fragment.FragmentUser;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import com.muxistudio.cardbanner.CardBanner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private static final String TAG = "main";
    private Toolbar mToolbar;
    private ImageButton btnHome;
    private ImageButton btnNewStory;
    private ImageButton btnUser;
    private TextView tvHome;
    private TextView tvUser;
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
        tvHome = (TextView)findViewById(R.id.tv_home_page);
        tvUser = (TextView)findViewById(R.id.tv_user_page);
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
        mFragmentHome = new FragmentHome();
        mFragmentUser = new FragmentUser();
        mFragmentList.add(mFragmentHome);
        mFragmentList.add(mFragmentUser);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
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
            case R.id.tv_home_page:
            case R.id.btn_home_page:
                mViewPager.setCurrentItem(0);
                btnHome.setColorFilter(ContextCompat.getColor(getBaseContext(),R.color.colorMain));
                btnUser.setColorFilter(ContextCompat.getColor(getBaseContext(),R.color.colorHint));
                break;
            case R.id.tv_user_page:
            case R.id.btn_user_page:
                mViewPager.setCurrentItem(1);
                btnHome.setColorFilter(ContextCompat.getColor(getBaseContext(),R.color.colorHint));
                btnUser.setColorFilter(ContextCompat.getColor(getBaseContext(),R.color.colorMain));
                break;
            case R.id.btn_new_story:
                newStory();
                break;
            default:
                break;
        }
    }
}
