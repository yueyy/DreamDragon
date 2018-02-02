package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.Constants;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserLogin;
import com.example.yueuy.dream.data.user.UserSignUpData;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.UserApi;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "signupactivity";
    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnSignUp;
    private TextView tvLogin;
    private SharedPreferencesUtils mPreferencesUtils;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }

    private void initView(){
        edtAccount = (EditText)findViewById(R.id.input_account);
        edtPassword = (EditText)findViewById(R.id.input_password);
        btnSignUp = (Button)findViewById(R.id.btn_sign_up);
        tvLogin = (TextView)findViewById(R.id.link_login);
        btnSignUp.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_sign_up:
                register();
                break;
            case R.id.link_login:
                login();
                break;
            default:
                break;
        }
    }

    private void register(){

        mPreferencesUtils = new SharedPreferencesUtils();
        final String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        if (account.isEmpty()||password.isEmpty()) {
            Toast.makeText(getBaseContext(),"请检查输入!",Toast.LENGTH_SHORT).show();
        }else{
            mPreferencesUtils.init(getBaseContext());
            mPreferencesUtils.set(Constants.USER,"account",account);
            mPreferencesUtils.set(account,"account",account);
            mPreferencesUtils.set(account,"password",password);
            User user = new User(account,password);
            OkHttpManager manager = new OkHttpManager();
            UserApi userApi = manager.getRetrofit().create(UserApi.class);
            userApi.signup(user).enqueue(new Callback<UserSignUpData>() {
                @Override
                public void onResponse(Call<UserSignUpData> call, Response<UserSignUpData> response)throws NullPointerException {
                    if (response.isSuccessful()) {
                        UserSignUpData signUpData = response.body();
                        Log.i(TAG, "onResponse: " + response.body());
                        if (signUpData.getMessage() == null){
                            mPreferencesUtils.set(account,"uid",signUpData.getUid());
                            Toast.makeText(getBaseContext(),"注册成功",Toast.LENGTH_SHORT).show();
                            login();
                        }else{
                            Log.i(TAG, "onResponse: error message");
                            Toast.makeText(getBaseContext(),"Has already been registered!",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Log.i(TAG, "onResponse: error register");
                        Toast.makeText(getBaseContext(),"error!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserSignUpData> call, Throwable t) {
                    Log.i(TAG, "onFailure: ");
                    Toast.makeText(getBaseContext(),"error register!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void login(){
        Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

}
