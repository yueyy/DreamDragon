package com.example.yueuy.dream.activity;

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
import com.example.yueuy.dream.UserDataManager;


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
    private UserDataManager mManager;
    private String token;
    private String expires_in;
    private String sns;


    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

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
        checkRemember();
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
            case R.id.check_remember_password:

                break;
            case R.id.link_forget_password:
                forgetPassword();
            default:
                break;

        }
    }

    private void login(){
        if (checkPassword()){
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            Toast.makeText(getBaseContext(),"success",Toast.LENGTH_SHORT).show();
            finish();
        }else{

        }
    }

    private boolean checkRemember(){
        boolean remember = false;

        return remember;
    }


    private boolean checkPassword(){
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        mPreferences = getPreferences(MODE_PRIVATE);
        mEditor = mPreferences.edit();
        boolean result = mManager.login(account,password);
        if (result) {
            mEditor.putString("account", account);
            mEditor.putString("password", password);
            mEditor.apply();
        }
        return true;
    }

    private void register(){
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
        finish();
    }

    private void forgetPassword(){
        Intent i = new Intent(LoginActivity.this,ForgetPwdActivity.class);
        startActivity(i);
        finish();
    }
}
