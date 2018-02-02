package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryData;

import java.util.List;

/**
 * Created by yueuy on 18-1-31.
 */

public class StoryRecycleAdapter extends RecyclerView.Adapter<StoryRecycleAdapter.ViewHolder> {

    private List<StoryData> mStoryData;
    private LayoutInflater mInflater;

    public StoryRecycleAdapter(Context context, List<StoryData> storyData){
        this.mInflater = LayoutInflater.from(context);
        this.mStoryData = storyData;

    }

    public void updateData(List<StoryData> newData){
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
    public void onBindViewHolder(ViewHolder viewHolder,int position){
        viewHolder.content.setText(mStoryData.get(position).getStory());
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
}
