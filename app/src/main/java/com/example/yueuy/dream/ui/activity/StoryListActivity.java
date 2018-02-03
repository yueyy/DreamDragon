package com.example.yueuy.dream.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.yueuy.dream.R;

/**
 * Created by yueuy on 18-2-3.
 */

public class StoryListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_list);
        initView();
        initData();
    }

    private void initView(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar_story_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.story_all_recycle);

    }

    private void initData(){

    }

}
