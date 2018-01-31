package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserId;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.UserApi;

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
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


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
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        if (account.isEmpty()||password.isEmpty()) {
            Toast.makeText(getBaseContext(),"请检查输入!",Toast.LENGTH_SHORT).show();
        }else{
            mPreferences = getPreferences(MODE_PRIVATE);
            mEditor = mPreferences.edit();
            mEditor.putString("username",account);
            mEditor.putString("password",password);
            mEditor.commit();

            User userdata = new User(account,password);
            final OkHttpManager manager = new OkHttpManager();
            UserApi userApi = manager.getRetrofit().create(UserApi.class);
            userApi.signup(userdata).enqueue(new Callback<UserId>() {
                @Override
                public void onResponse(Call<UserId> call, Response<UserId> response) {
                    UserId userId = response.body();
                    mEditor.putInt("userId",userId.getUid());
                    mEditor.putString("token",userId.getToken());
                    mEditor.commit();
                }

                @Override
                public void onFailure(Call<UserId> call, Throwable t) {

                }
            });
        }
    }

    private void login(){
        Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void success(){
        Intent i = new Intent(SignUpActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
