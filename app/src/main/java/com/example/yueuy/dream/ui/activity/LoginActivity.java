package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.User;
import com.example.yueuy.dream.data.user.UserAuth;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.UserService;
import com.example.yueuy.dream.ui.fragment.FragmentUser;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.io.IOException;

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
                try {
                    check();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.link_create_account:
                register();
                break;
            default:
                break;

        }
    }

    private void check()throws IOException{
        mPreferencesUtils = new SharedPreferencesUtils();
        mPreferencesUtils.init(getBaseContext());
        String account = edtAccount.getText().toString();
        String password = edtPassword.getText().toString();
        User user = new User(account,password);
        UserService userService = ServiceGenerator.createService(UserService.class);
        Call<UserAuth> call = userService.login(user);
        call.enqueue(new Callback<UserAuth>() {
            @Override
            public void onResponse(Call<UserAuth> call, Response<UserAuth> response) {
                UserAuth auth = response.body();
                mPreferencesUtils.setUser("token",auth.getToken());
                mPreferencesUtils.setUser("uid",auth.getUid());
                Log.i(TAG, "check: "+auth.getUid());
                login(auth.getToken());
            }

            @Override
            public void onFailure(Call<UserAuth> call, Throwable t) {

            }
        });

    }

    private void login(String token){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        i.putExtra("token",token);
        startActivity(i);
//        startActivityForResult(i,requestCode);
        finish();
    }

    private void register(){
        Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivityForResult(i,1);
        finish();
    }


}
