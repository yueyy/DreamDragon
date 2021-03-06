package com.example.yueuy.dream.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.story.StoryRecycleAdapter;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SpaceItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentHome extends Fragment {

    private TextView storyContent;
    private StoryRecycleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private StoryRandom mStoryData;
    private SwipeRefreshLayout mRefreshLayout;
    private List<StoryRandom.RandomBean> random;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page,null);
        mRecyclerView = view.findViewById(R.id.story_recycle);
        mRefreshLayout = view.findViewById(R.id.refresh_view);
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);

        refresh();

//        下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                        if (mRefreshLayout.isRefreshing()){
                            mRefreshLayout.setRefreshing(false);
                        }
                    }
                },3000);

            }
        });
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private void refresh(){
        StoryService storyService = ServiceGenerator.createService(StoryService.class);
        storyService.showRandom().enqueue(new Callback<StoryRandom>() {
            @Override
            public void onResponse(Call<StoryRandom> call, Response<StoryRandom> response) {
                StoryRandom list = response.body();
                try {
                    random = list.getRandom();
                    mAdapter = new StoryRecycleAdapter(getContext(),random);
                    mRecyclerView.setAdapter(mAdapter);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<StoryRandom> call, Throwable t) {

            }
        });
    }
}
