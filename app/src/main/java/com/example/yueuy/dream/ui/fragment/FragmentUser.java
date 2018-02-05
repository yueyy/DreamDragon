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
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.ui.activity.LoginActivity;
import com.example.yueuy.dream.ui.activity.StoryJoinActivity;
import com.example.yueuy.dream.ui.activity.StoryMainActivity;
import com.example.yueuy.dream.ui.activity.StoryStartActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentUser extends Fragment {

    private static final String TAG = "fragmentUser";
    private Toolbar mToolbar;
    private TextView mLogin;
    private TextView mLogout;
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
        final String username = mPreferencesUtils.getUser("username");
        uid = mPreferencesUtils.getUserId("uid");
        token = mPreferencesUtils.getUser("token");
        if (username.equals("unknown")) {
            mUser.setText(username);                        // 刷新帐号名
        }else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://120.78.194.125:2000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService service = retrofit.create(UserService.class);
            Call<UserData> call = service.showMyData(uid,token);
            call.enqueue(new Callback<UserData>() {
                @Override
                public void onResponse(Call<UserData> call, Response<UserData> response) {
                    mUserData = response.body();
                    Log.i(TAG, "onResponse: "+mUserData.toString());
                    mUser.setText(username);
                    mJoinNum.setText(String.valueOf(mUserData.getUsa()));                 // 刷新参与数
                    mStartNum.setText(String.valueOf(mUserData.getUsb()));                // 刷新发起数
                    mLikeNum.setText(String.valueOf(mUserData.getUserlikenum()));         // 刷新被赞数
                    mWords.setText(String.valueOf(mUserData.getUserwords()));             // 刷新总字数
                    Log.i(TAG, "onResponse: "+response.code()+response.headers());
                }

                @Override
                public void onFailure(Call<UserData> call, Throwable t) {

                }
            });
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
        mStartNum = view.findViewById(R.id.user_start_story);
        mJoinNum = view.findViewById(R.id.user_join_story);
        mLikeNum = view.findViewById(R.id.user_like);
        mLogin = view.findViewById(R.id.user_login);
        mLogout = view.findViewById(R.id.user_logout);

        mUser = view.findViewById(R.id.user_name);
        mIntro = view.findViewById(R.id.user_intro);

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

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPreferencesUtils.removeUser();
                Intent i = new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StoryStartActivity.class);
                startActivity(i);
            }
        });

        mJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), StoryJoinActivity.class);
                startActivity(i);
            }
        });
    }

}
