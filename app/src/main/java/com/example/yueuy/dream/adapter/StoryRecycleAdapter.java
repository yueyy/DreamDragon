package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.ui.activity.StoryActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yueuy on 18-1-31.
 */

public class StoryRecycleAdapter extends RecyclerView.Adapter<StoryRecycleAdapter.ViewHolder> {

    private Context mContext;
    private List<StoryRandom> mStoryData;
    private LayoutInflater mInflater;
    boolean clickLike = false;
    private SharedPreferencesUtils mPreferencesUtils;

    public StoryRecycleAdapter(Context context, List<StoryRandom> storyData){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mStoryData = storyData;

    }

    public void updateData(List<StoryRandom> newData){
        mStoryData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        return mStoryData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent ,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story_random,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position){
        viewHolder.content.setText(mStoryData.get(position).getRandom().get(position).getStory());
        viewHolder.likenum.setText(mStoryData.get(position).getRandom().get(position).getLikenum());
        viewHolder.keywordFirst.setText(mStoryData.get(position)
                .getRandom().get(position)
                .getKeyword().get(position)
                .getKeyword1());
        viewHolder.keywordSecond.setText(mStoryData.get(position)
                .getRandom().get(position)
                .getKeyword().get(position)
                .getKeyword2());
        final int storyid = mStoryData.get(position).getRandom().get(position).getStoryid();

        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryService storyService = ServiceGenerator.createService(StoryService.class);
                storyService.showStory(storyid).enqueue(new Callback<StoryData>() {
                    @Override
                    public void onResponse(Call<StoryData> call, Response<StoryData> response) {
                        if (response.isSuccessful()) {
                            StoryData data = response.body();
                            showStory(data);
                        }
                    }

                    @Override
                    public void onFailure(Call<StoryData> call, Throwable t) {

                    }
                });
            }
        });

        viewHolder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    viewHolder.like.setColorFilter(Color.RED);
                    String token = mPreferencesUtils.getUser("token");
                    StoryService storyService = ServiceGenerator.createService(StoryService.class);
                    storyService.like(storyid,token).enqueue(new Callback<StoryLike>() {
                        @Override
                        public void onResponse(Call<StoryLike> call, Response<StoryLike> response) {
                            int likenum = response.body().getLikenum();
                            viewHolder.likenum.setText(likenum);
                        }

                        @Override
                        public void onFailure(Call<StoryLike> call, Throwable t) {
                            Toast.makeText(v.getContext(),"点赞失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content,keywordFirst,keywordSecond,likenum,talkingnum;
        ImageButton like,talking;


        public ViewHolder(View itemView){
            super(itemView);
            content = itemView.findViewById(R.id.random_content);
            keywordFirst = itemView.findViewById(R.id.random_keyword_first);
            keywordSecond = itemView.findViewById(R.id.random_keyword_second);
            likenum = itemView.findViewById(R.id.random_like);
            talkingnum = itemView.findViewById(R.id.random_talking);
            like = itemView.findViewById(R.id.random_like_img);
            talking = itemView.findViewById(R.id.random_talking_img);
        }
    }

    private void showStory(StoryData storyData){
        Intent i = new Intent(mContext,StoryActivity.class);
        Bundle b = new Bundle();
        b.putString("story",storyData.getStory());
        b.putInt("likenum",storyData.getLikenum());
        b.putString("username",storyData.getUsername());
        b.putString("keyword1",storyData.getKeyword().get(1).getKeyword1());
        mContext.startActivity(i);
    }


}
