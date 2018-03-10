package com.example.yueuy.dream.adapter.story;

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

public class StoryKeyAdapter extends RecyclerView.Adapter<StoryKeyAdapter.ViewHolder> {


    private List<StoryData.KeywordsBean> mDataList;
    private LayoutInflater mInflater;
    private SharedPreferencesUtils mPreferencesUtils;

    public StoryKeyAdapter(Context context, List<StoryData.KeywordsBean> dataList) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataList = dataList;
    }

    @Override
    public int getItemCount(){
        return mDataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keywords,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position){
        viewHolder.storyKey.setText(mDataList.get(position).getKeyword());
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView storyKey;

        public ViewHolder(View view){
            super(view);
            storyKey = view.findViewById(R.id.main_keyword);
        }
    }
}
