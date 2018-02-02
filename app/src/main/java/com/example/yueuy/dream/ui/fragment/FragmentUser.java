package com.example.yueuy.dream.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.ui.activity.LoginActivity;
import com.example.yueuy.dream.ui.activity.SignUpActivity;
import com.example.yueuy.dream.ui.activity.StoryActivity;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentUser extends Fragment{

    private Toolbar mToolbar;
    private TextView mLogin;
    private TextView mUser, mIntro,mStartNum,mJoinNum,mLikeNum,mWords;
    private LinearLayout mStart;
    private LinearLayout mJoin;
    private LinearLayout mHot;
    private LinearLayout mSetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_page,null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(View view){
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mToolbar = view.findViewById(R.id.toolbar_user);
        mToolbar.setTitle(R.string.tab_user);

        mLogin = view.findViewById(R.id.user_login);
        mUser = view.findViewById(R.id.user_name);
        mIntro = view.findViewById(R.id.user_intro);
        mStartNum = view.findViewById(R.id.user_start_story);
        mJoinNum = view.findViewById(R.id.user_join_story);
        mLikeNum = view.findViewById(R.id.user_like);
        mWords = view.findViewById(R.id.user_all_words);
        mStart = view.findViewById(R.id.ll_my_start_story);
        mJoin = view.findViewById(R.id.ll_my_join_story);
        mHot = view.findViewById(R.id.ll_my_hot_story);
        mSetting = view.findViewById(R.id.ll_setting);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StoryActivity.class);
                startActivity(i);
            }
        });
    }
}
