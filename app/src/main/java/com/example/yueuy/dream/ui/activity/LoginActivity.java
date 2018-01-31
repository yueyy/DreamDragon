package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.UserApi;


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
                login();
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

    private void login(){
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        if ( account.isEmpty() || password.isEmpty() ){
            Toast.makeText(getBaseContext(),"请检查输入!",Toast.LENGTH_SHORT).show();
        }else if (mRememberPwd.isChecked()){
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",account);
            editor.putString("password",password);
            editor.apply();
        }else {
            if (checkPassword()) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(getBaseContext(), "登录成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getBaseContext(),"ERROR!",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean checkPassword(){
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        OkHttpManager manager = new OkHttpManager();
        UserApi userApi = manager.getRetrofit().create(UserApi.class);
//        userApi.signin(new User(account,password)).enqueue(new Callback<UserId>() {
//            @Override
//            public void onResponse(Call<UserId> call, Response<UserId> response) {
//                if (response.isSuccessful()){
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserId> call, Throwable t) {
//
//            }
//        });
        return true;
    }

    private void register(){
        Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }

//    private void forgetPassword(){
//        Intent i = new Intent(LoginActivity.this,ForgetPwdActivity.class);
//        startActivity(i);
//        finish();
//    }
}
