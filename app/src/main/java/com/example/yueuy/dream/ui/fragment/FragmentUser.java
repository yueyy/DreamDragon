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
import com.example.yueuy.dream.data.Constants;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.ui.activity.LoginActivity;
import com.example.yueuy.dream.ui.activity.StoryListActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentUser extends Fragment{

    private static final String TAG = "fragmentUser";
    private Toolbar mToolbar;
    private TextView mLogin;
    private TextView mUser, mIntro,mStartNum,mJoinNum,mLikeNum,mWords;
    private LinearLayout mStart;
    private LinearLayout mJoin;
    private LinearLayout mHot;
    private LinearLayout mSetting;
    private SharedPreferencesUtils mPreferencesUtils;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_page,null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initData();
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
                Intent i = new Intent(getActivity(), StoryListActivity.class);
                startActivity(i);
            }
        });
    }

    private void initData(){
        mPreferencesUtils = new SharedPreferencesUtils();
        String token = mPreferencesUtils.getUser("token");
        if (token!=null) {
            String username = mPreferencesUtils.getUser("username");
            mUser.setText(username);
            UserService userService = ServiceGenerator.createService(UserService.class);
            userService.showMyData(token).enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
                    if (response.isSuccessful()) {
                        UserData userData = response.body();
                        mJoinNum.setText(userData.getUsa());    //用户参与的所有故事数
                        mStartNum.setText(userData.getUsb());    //用户发起的所有故事数
                        mLikeNum.setText(userData.getUserlikenum());     // 用户获赞数
                        mWords.setText(userData.getUserwords());    // 用户总字数
                    }else {
                        Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserData> call, Throwable t) {
                    Log.i(TAG, "onFailure: ");
                }
            });
        }
    }

}
