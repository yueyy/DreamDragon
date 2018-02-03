package com.example.yueuy.dream.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.adapter.StoryRecycleAdapter;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.util.SpaceItemDecoration;
import com.muxistudio.cardbanner.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentHome extends Fragment {

    private TextView storyContent;
    private StoryRecycleAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private List<StoryData> mStoryData;
    private List<ViewHolder<Integer>> mHolders = new ArrayList<>();
    private List<Integer> resIdList = new ArrayList<>();
    private Integer[] resIds = {R.drawable.a,R.drawable.b,R.drawable.c};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page,null);
        mRecyclerView = view.findViewById(R.id.story_recycle);
        mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        mStoryData = initData();
        mAdapter = new StoryRecycleAdapter(getContext(),mStoryData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(5));


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private List<StoryData> initData(){
        List<StoryData> data = new ArrayList<>();
        StoryData story = new StoryData();
        story.setStory("在很久很久以前");
        data.add(story);
        StoryData storya = new StoryData();
        storya.setStory("有一个人啊");
        data.add(storya);
        StoryData storyb = new StoryData();
        story.setStory("他想吃鸡");
        data.add(storyb);



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

//    private List<StoryData> initData(){
//        OkHttpManager manager = new OkHttpManager();
//        StoryService storyApi = manager.getRetrofit().create(StoryService.class);
//        storyApi.showRank().enqueue(new Callback<StoryRank>() {
//            @Override
//            public void onResponse(Call<StoryRank> call, Response<StoryRank> response) {
//                StoryRank rank = response.body();
//
//            }
//            @Override
//            public void onFailure(Call<StoryRank> call, Throwable t) {
//
//            }
//        });
//    }
        return data;
    }

}
