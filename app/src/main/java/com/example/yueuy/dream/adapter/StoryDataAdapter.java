package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by yueuy on 18-2-5.
 */

public class StoryDataAdapter extends RecyclerView.Adapter<StoryDataAdapter.ViewHolder> {


    private List<StoryData.StorycBean> mDataList;
    private LayoutInflater mInflater;
    private SharedPreferencesUtils mPreferencesUtils;

    public StoryDataAdapter(Context context, List<StoryData.StorycBean> dataList) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
    }

    @Override
    public int getItemCount(){
        return mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_stoyc,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.storyContent.setText(mDataList.get(position).getStoryc());
        viewHolder.storyUsername.setText(mDataList.get(position).getUsernamec());
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView storyContent;
        private TextView storyUsername;

        public ViewHolder(View view){
            super(view);
            storyContent = view.findViewById(R.id.main_storyc_content);
            storyUsername = view.findViewById(R.id.main_storyc_username);
        }
    }
}
