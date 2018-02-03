package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.MainWordAdapter;
import com.example.yueuy.dream.data.story.Keyword;
import com.example.yueuy.dream.data.story.StoryId;
import com.example.yueuy.dream.data.story.StoryWrite;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import com.example.yueuy.dream.util.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class NewStoryActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "new story";
    private Toolbar mToolbar;
    private EditText edtMainWord;
    private EditText edtContent;
    private ImageButton btnCancel;
    private ImageButton btnPublish;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private TextView tvLeftWords;
    private List<Integer> mainList ;
    private MainWordAdapter mAdapter;
    private SharedPreferencesUtils mPreferencesUtils;
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
        mToolbar = (Toolbar)findViewById(R.id.toolbar_edit);
//        tvLeftWords = (TextView)findViewById(R.id.left_words);
//        tvLeftWords.setText(String.valueOf(MAX_WORDS));
        setMainWordArray();
        btnCancel.setOnClickListener(this);
        btnPublish.setOnClickListener(this);
        mRecyclerView.setOnClickListener(this);

//        mToolbar.setTitle(R.string.tv_user_join);
//        setSupportActionBar(mToolbar);
//        mToolbar.setTitleTextColor(ContextCompat.getColor(getBaseContext(),R.color.colorMain));
//        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(NewStoryActivity.this,MainActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
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
        mPreferencesUtils = new SharedPreferencesUtils();
        mPreferencesUtils.init(getBaseContext());
        mPreferencesUtils.setStory("story",content);

        StoryService storyService = ServiceGenerator.createService(StoryService.class,"token");
        String token = mPreferencesUtils.getUser("token");

        StoryWrite newStory = new StoryWrite();
        newStory.setStory(content);
        Log.i(TAG, "publish: "+mPreferencesUtils.getUserId("uid"));
        newStory.setUid(mPreferencesUtils.getUserId("uid"));

        Keyword keyword = new Keyword();
        keyword.setKeyword1(mPreferencesUtils.getStory("keyword1"));


        Call<StoryId> storyDataCall = storyService.newStory(newStory,token);
        storyDataCall.enqueue(new Callback<StoryId>() {
            @Override
            public void onResponse(Call<StoryId> call, Response<StoryId> response) {
                if (response.isSuccessful()) {
                    mPreferencesUtils.setStory("storyid", response.body().getStoryid());
                    Toast.makeText(getBaseContext(), "您的故事 未完待续...", Toast.LENGTH_SHORT).show();
                    returnHomePage();
                }else {
                    Log.i(TAG, "onResponse: error!");
                }
            }

            @Override
            public void onFailure(Call<StoryId> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });

    }

    private void setMainWordArray(){
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

//        mRecyclerView.setAdapter(mAdapter);
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
