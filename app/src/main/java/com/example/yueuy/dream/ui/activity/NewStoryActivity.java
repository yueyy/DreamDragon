package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.MainWordAdapter;
import com.example.yueuy.dream.util.SpaceItemDecoration;
import com.example.yueuy.dream.data.story.NewStoryData;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.net.OkHttpManager;
import com.example.yueuy.dream.net.api.StoryApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class NewStoryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtMainWord;
    private EditText edtContent;
    private ImageButton btnCancel;
    private ImageButton btnPublish;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private TextView tvLeftWords;
    private List<Integer> mainList ;
    private MainWordAdapter mAdapter;
    private static final int MAX_WORDS = 100;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        initView();

    }

    private void initView(){
        edtContent = (EditText)findViewById(R.id.edt_content);
        btnCancel = (ImageButton)findViewById(R.id.btn_cancel);
        btnPublish = (ImageButton)findViewById(R.id.btn_publish);
        mRecyclerView = (RecyclerView)findViewById(R.id.main_word_recycle);
        edtMainWord = (EditText)findViewById(R.id.edt_keyword);
//        tvLeftWords = (TextView)findViewById(R.id.left_words);
//        tvLeftWords.setText(String.valueOf(MAX_WORDS));
        setMainWordArray();
        btnCancel.setOnClickListener(this);
        btnPublish.setOnClickListener(this);
        mRecyclerView.setOnClickListener(this);

    }

    private void changeLeftWords() {
        edtMainWord.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark));
        edtMainWord.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
//        TextWatcher watcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                if (s.length()>MAX_WORDS){
//                    s.delete(MAX_WORDS,s.length());
//                }else {
//                    int left = MAX_WORDS - s.length();
//                    tvLeftWords.setText(String.valueOf(left));
//                }
//            }
//        };
//        edtContent.addTextChangedListener(watcher);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.edt_content:
                changeLeftWords();
                break;
            case R.id.btn_cancel:
                returnHomePage();
                break;
            case R.id.btn_publish:
                publish();
                break;
            case R.id.main_word_recycle:
                setMainWordChange();
            default:
                break;
        }
    }
    private void publish(){
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

    private void setMainWordArray(){
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        mainList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mainList.add(i,i+1);
        }
        mAdapter = new MainWordAdapter(getBaseContext(),mainList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
    }

    private void setMainWordChange(){

    }
    private void returnHomePage(){
        Intent i = new Intent(NewStoryActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
