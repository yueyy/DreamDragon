package com.example.yueuy.dream.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.StoryRecycleAdapter;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.data.story.StoryRank;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.util.SpaceItemDecoration;
import com.muxistudio.cardbanner.ViewHolder;

import java.util.ArrayList;
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

    private List<ViewHolder<Integer>> mHolders = new ArrayList<>();
    private List<Integer> resIdList = new ArrayList<>();
    private Integer[] resIds = {R.drawable.a,R.drawable.b,R.drawable.c};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page,null);
        mRecyclerView = view.findViewById(R.id.story_recycle);
        mRefreshLayout = view.findViewById(R.id.refresh_view);
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        refresh();
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mRefreshLayout.isRefreshing()){

                }else {
                    refresh();
                }
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
//        for (int i = 0; i < 3; i++) {
//            ViewHolder<Integer> viewHolder = new ViewHolder<Integer>() {
//                @Override
//                public View getView(Context context, Integer integer) {
//                    ImageView imageView = new ImageView(MainActivity.this);
//                    imageView.setImageResource(integer);
//                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                    return imageView;
//                }
//            };
//            mHolders.add(viewHolder);
//        }
//        resIdList = Arrays.asList(resIds);
//        mBanner.setViewHolders(mHolders,resIdList);
//        mBanner.setAutoScroll(true);
//        mBanner.setScrollDuration(3000);
//        mBanner.setScrollTime(1000);
