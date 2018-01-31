package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story_rank,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,int position){
        viewHolder.title.setText(mStoryData.get(position).getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;

        public ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
