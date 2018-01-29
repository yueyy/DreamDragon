package com.example.yueuy.dream.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;

/**
 * Created by yueuy on 18-1-29.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtAccount;
    private EditText edtPassword;
    private Button btnSignUp;
    private TextView tvLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
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
        if (check()) {
            Toast.makeText(getBaseContext(), "success!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getBaseContext(),"failed!",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean check(){
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();

        return true;
    }
    private void login(){
        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}
