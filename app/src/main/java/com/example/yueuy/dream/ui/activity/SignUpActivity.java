package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserId;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.UserService;
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
            mPreferencesUtils.setUser("account",account);
            mPreferencesUtils.setUser("password",password);

            User user = new User(account,password);
            UserService userService = ServiceGenerator.createService(UserService.class);
            userService.signup(user).enqueue(new Callback<UserId>() {
                @Override
                public void onResponse(Call<UserId> call, Response<UserId> response) {
                    if (response.isSuccessful()){
                        mPreferencesUtils.setUser("uid",response.body().getUid());
                        if (response.body().getMessage()!=null){
                            Toast.makeText(getBaseContext(),"注册成功!",Toast.LENGTH_SHORT).show();
                            login();
                        }else {
                            Toast.makeText(getBaseContext(),"用户已注册!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Log.i(TAG, "onResponse: error");
                        Toast.makeText(getBaseContext(),"error!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserId> call, Throwable t) {
                    Log.i(TAG, "onFailure: ");
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
