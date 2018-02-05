package com.example.yueuy.dream.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryId;
import com.example.yueuy.dream.data.story.StoryWrite;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yueuy on 18-1-29.
 */

public class NewStoryActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "new story";
    private Toolbar mToolbar;
    private EditText edtContent;
    private ImageButton btnCancel;
    private ImageButton btnPublish;
    private LinearLayoutManager manager;
    private TextView tvLeftWords;
    private List<Integer> mainList;
    private LinearLayout mLayout;
    private TextInputEditText key1,key2,key3,key4,key5,key6,key7,key8;
    private List<StoryWrite> mStory;
    private List<StoryWrite.KeywordBean> keywords;
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
        mToolbar = (Toolbar)findViewById(R.id.toolbar_edit);
        key1 = (TextInputEditText)findViewById(R.id.edt_keyword);
        key2 = (TextInputEditText)findViewById(R.id.edt_keyword_2);
        key3 = (TextInputEditText)findViewById(R.id.edt_keyword_3);
        key4 = (TextInputEditText)findViewById(R.id.edt_keyword_4);
        key5 = (TextInputEditText)findViewById(R.id.edt_keyword_5);
        key6 = (TextInputEditText)findViewById(R.id.edt_keyword_6);
        key7 = (TextInputEditText)findViewById(R.id.edt_keyword_7);
        key8 = (TextInputEditText)findViewById(R.id.edt_keyword_8);

        btnCancel.setOnClickListener(this);
        btnPublish.setOnClickListener(this);

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



    @Override
    public void onClick(View v){
        switch (v.getId()){
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
        String content = edtContent.getText().toString();
        mPreferencesUtils = new SharedPreferencesUtils();
        mPreferencesUtils.init(getBaseContext());
        mPreferencesUtils.setStory("story",content);

        //       添加关键字
        StoryWrite.KeywordBean keyword = new StoryWrite.KeywordBean();
        keyword.setKeyword1(setKeyword(key1));
        keyword.setKeyword2(setKeyword(key2));
        keyword.setKeyword3(setKeyword(key3));
        keyword.setKeyword4(setKeyword(key4));
        keyword.setKeyword5(setKeyword(key5));
        keyword.setKeyword6(setKeyword(key6));
        keyword.setKeyword7(setKeyword(key7));
        keyword.setKeyword8(setKeyword(key8));

        String token = mPreferencesUtils.getUser("token");
        String uid = String.valueOf(mPreferencesUtils.getUserId("uid"));
        if (token.equals("unknown")) {
            Toast.makeText(getBaseContext(), "没有登录用户", Toast.LENGTH_SHORT).show();
        }else {
            StoryWrite newStory = new StoryWrite();
            newStory.setStory(content);
            newStory.setKeyword(keyword);
            newStory.setUid(uid);
            Log.i(TAG, "publish: " + mPreferencesUtils.getUserId("uid"));

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://120.78.194.125:2000")
                    .build();
            StoryService service = retrofit.create(StoryService.class);
            Call<StoryId> storyDataCall = service.newStory(newStory,token);

            storyDataCall.enqueue(new Callback<StoryId>() {
                @Override
                public void onResponse(Call<StoryId> call, Response<StoryId> response) {
                    Log.i(TAG, "onResponse: "+response .body()+response.code());
                    if (response.code()==200) {
                        mPreferencesUtils.setStory("storyid", response.body().getStoryid());
                        Toast.makeText(getBaseContext(), "你想要怎样的结局", Toast.LENGTH_SHORT).show();

                    } else {
                        Log.i(TAG, "onResponse: error!");
                        Toast.makeText(getBaseContext(), "服务器好像有点故障...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<StoryId> call, Throwable t) {
                    Log.i(TAG, "onFailure: new story ");
                    Toast.makeText(getBaseContext(), "请先登录", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String setKeyword(TextInputEditText key){
        String k = key.getText().toString();
        if (k.isEmpty()){
            return "";
        }else {
            return k;
        }
    }

    private void returnHomePage(){
        Intent i = new Intent(NewStoryActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }


}
