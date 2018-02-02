package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserData;
import com.example.yueuy.dream.data.user.UserLogin;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.UserApi;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by yueuy on 18-1-29.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnLogin;
    private CheckBox mRememberPwd ;
    private TextView tvNewAccount;
    private TextView tvForgetPwd;
    private User mUser;
    private SharedPreferencesUtils mPreferencesUtils;
    private static final String TAG = "login";


    @Override
    public void onCreate(Bundle savesInstanceState){
        super.onCreate(savesInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        edtAccount = (EditText)findViewById(R.id.edt_account);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        mRememberPwd = (CheckBox)findViewById(R.id.check_remember_password);
        tvNewAccount = (TextView)findViewById(R.id.link_create_account);
        tvForgetPwd = (TextView)findViewById(R.id.link_forget_password);

        btnLogin.setOnClickListener(this);
        tvNewAccount.setOnClickListener(this);
        tvForgetPwd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                check();
                break;
            case R.id.link_create_account:
                register();
                break;
//            case R.id.link_forget_password:
//                forgetPassword();
            default:
                break;

        }
    }

    private void check(){
        mPreferencesUtils = new SharedPreferencesUtils();
        mPreferencesUtils.init(getBaseContext());
        final String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        OkHttpManager manager = new OkHttpManager();
        UserApi userApi = manager.getRetrofit().create(UserApi.class);
        mUser = new User(account,password);
        userApi.login(mUser).enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()) {
                    UserLogin login = response.body();
                    mPreferencesUtils.set(account,"token",login.getToken());
                    Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    login();
                }else {
                    Log.i(TAG, "onResponse: login error");
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }

    private void login(){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    private void register(){
        Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }
//
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//        if (requestCode == 200)
//    }

//    private void forgetPassword(){
//        Intent i = new Intent(LoginActivity.this,ForgetPwdActivity.class);
//        startActivity(i);
//        finish();
//    }
}
