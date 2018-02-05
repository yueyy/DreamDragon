package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.UserStart;
import com.example.yueuy.dream.ui.activity.StoryMainActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;

import java.util.List;

/**
 * Created by yueuy on 18-2-5.
 */

public class UserStartAdapter extends RecyclerView.Adapter<UserStartAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private SharedPreferencesUtils mPreferencesUtils;
    private List<UserStart.Start> mList;

    public UserStartAdapter(Context context,List<UserStart.Start> story){
        this.mContext = context;
        this.mList = story;
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_story_content,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.content.setText(mList.get(position).getStory());
        //      点击查看故事详情
        final int storyid = mList.get(position).getStoryid();
        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),StoryMainActivity.class);
                i.putExtra("storyid",storyid);
                v.getContext().startActivity(i);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView like,talking;

        public ViewHolder(View itemView){
            super(itemView);
            content = itemView.findViewById(R.id.main_story_content);
            like = itemView.findViewById(R.id.story_like);
            talking = itemView.findViewById(R.id.story_talking);
        }
    }
}
