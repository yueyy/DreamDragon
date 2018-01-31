package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.NewStoryData;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.StoryApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class NewStoryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTitle;
    private EditText edtContent;
    private ImageButton btnCancel;
    private ImageButton btnPublish;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        initView();

    }

    private void initView(){
        edtTitle = (EditText)findViewById(R.id.edt_title);
        edtContent = (EditText)findViewById(R.id.edt_content);
        btnCancel = (ImageButton)findViewById(R.id.btn_cancel);
        btnPublish = (ImageButton)findViewById(R.id.btn_publish);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.edt_title:

                break;
            case R.id.edt_content:

                break;
            case R.id.btn_cancel:
                returnHomePage();
                break;
            case R.id.btn_publish:
                publish();
                break;
            default:
                break;
        }
    }
    private void publish(){
        String title = edtTitle.getText().toString();
        String content = edtContent.getText().toString();

        StoryData storyData = new StoryData();
        OkHttpManager manager = new OkHttpManager();
        StoryApi storyApi = manager.getRetrofit().create(StoryApi.class);
        storyApi.newStory(storyData).enqueue(new Callback<NewStoryData>() {
            @Override
            public void onResponse(Call<NewStoryData> call, Response<NewStoryData> response) {
                int storyid = response.body().getStoryid();


            }

            @Override
            public void onFailure(Call<NewStoryData> call, Throwable t) {
                Toast.makeText(getBaseContext(),"出现了一点问题",Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getBaseContext(),"您的故事 未完待续...",Toast.LENGTH_SHORT).show();
        returnHomePage();
    }

    private void returnHomePage(){
        Intent i = new Intent(NewStoryActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
