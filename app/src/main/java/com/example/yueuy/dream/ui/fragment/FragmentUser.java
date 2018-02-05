package com.example.yueuy.dream.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.FragmentAdapter;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.ui.activity.LoginActivity;
import com.example.yueuy.dream.ui.activity.StoryMainActivity;
import com.example.yueuy.dream.ui.activity.StoryStartActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentUser extends Fragment {

    private static final String TAG = "fragmentUser";
    private Toolbar mToolbar;
    private TextView mLogin;
    private TextView mUser, mIntro, mStartNum, mJoinNum, mLikeNum, mWords;
    private LinearLayout mStart;
    private LinearLayout mJoin;
    private LinearLayout mHot;
    private LinearLayout mSetting;
    private SharedPreferencesUtils mPreferencesUtils;
    private UserData mUserData;
    private int uid;
    private String token;
    private boolean refresh = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_page, null);
        initView(view);

        mPreferencesUtils = new SharedPreferencesUtils();
        String username = mPreferencesUtils.getUser("username");
        uid = mPreferencesUtils.getUserId("uid");
        UserService userService = ServiceGenerator.createService(UserService.class);
        userService.showMyData(uid,token).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.isSuccessful()) {
                    mUserData = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

        if (username.equals("unknown")) {
            mUser.setText(username);                        // 刷新帐号名
        }else {
            mUser.setText(username);
            mJoinNum.setText(mUserData.getUsa());                 // 刷新参与数
            mStartNum.setText(mUserData.getUsb());                // 刷新发起数
            mLikeNum.setText(mUserData.getUserlikenum());         // 刷新被赞数
            mWords.setText(mUserData.getUserwords());             // 刷新总字数
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView(final View view) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
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
                if (mPreferencesUtils.getUser("token").equals("unknown")) {
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(view.getContext(),"你已经登录过啦",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StoryStartActivity.class);
                startActivity(i);
            }
        });
    }

//    private void initData(View view) {
//        mPreferencesUtils = new SharedPreferencesUtils();
//        String username = mPreferencesUtils.getUser("username");
//        uid = mPreferencesUtils.getUserId("uid");
//        UserService userService = ServiceGenerator.createService(UserService.class);
//        userService.showMyData(uid,token).enqueue(new Callback<UserData>() {
//            @Override
//            public void onResponse(Call<UserData> call, Response<UserData> response) {
//                if (response.isSuccessful()) {
//                    mUserData = response.body();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserData> call, Throwable t) {
////                Toast.makeText(view.getContext(), "请先登录!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        if (username.equals("unknown")) {
//            mUser.setText(username);                        // 刷新帐号名
//        }else {
//            mUser.setText(username);
//            mJoinNum.setText(mUserData.getUsa());                 // 刷新参与数
//            mStartNum.setText(mUserData.getUsb());                // 刷新发起数
//            mLikeNum.setText(mUserData.getUserlikenum());         // 刷新被赞数
//            mWords.setText(mUserData.getUserwords());             // 刷新总字数
//        }
//    }

}
