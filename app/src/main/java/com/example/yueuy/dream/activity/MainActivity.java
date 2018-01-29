package com.example.yueuy.dream.activity;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.FragmentAdapter;
import com.example.yueuy.dream.fragment.FragmentHome;
import com.example.yueuy.dream.fragment.FragmentUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private Toolbar mToolbar;
    private ImageButton btnHome;
    private ImageButton btnNewStory;
    private ImageButton btnUser;
    private ViewPager mViewPager;
    private FragmentAdapter mFragmentAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPage();
    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        btnHome = (ImageButton)findViewById(R.id.btn_home_page);
        btnUser = (ImageButton) findViewById(R.id.btn_user_page);
        btnNewStory = (ImageButton) findViewById(R.id.new_story);

        mToolbar.setTitle(R.string.app_name);
    }

    private void initPage(){

        FragmentHome fragmentHome = new FragmentHome();
        FragmentUser fragmentUser = new FragmentUser();
        mFragmentList.add(fragmentHome);
        mFragmentList.add(fragmentUser);
        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setOnClickListener(this);

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
            case R.id.new_story:

                break;
            default:
                break;
        }
    }
}
