package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryData;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by yueuy on 18-2-4.
 */

public abstract class StoryListAdapter<T> extends RecyclerView.Adapter<StoryListAdapter.ViewHolder> {



    private List<T> mDataList;
    private LayoutInflater mInflater;
    boolean clickLike = false;
    private SharedPreferencesUtils mPreferencesUtils;

    public StoryListAdapter(Context context, List<T> dataList){
        this.mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;

    }

    public void updateData(List<T> newData){
        mDataList = newData;
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    public void setDataList(List<T> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public int getItemCount(){
        return mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story_content,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.storyContent.setText(mDataList.get(position).toString());
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView storyContent;
        private RecyclerView mRecyclerView;
        private TextView storyLikeNum,storyTalkingNum;
        private ImageButton storyLike,storyTalking;

        public ViewHolder(View view){
            super(view);
            storyContent = view.findViewById(R.id.main_story_content);
            mRecyclerView = view.findViewById(R.id.keywords_recycle);
            storyLike = view.findViewById(R.id.story_like);
            storyTalking = view.findViewById(R.id.story_talking);
            storyLikeNum = view.findViewById(R.id.story_like_img);
            storyTalkingNum = view.findViewById(R.id.story_talking_img);
        }
    }
}
