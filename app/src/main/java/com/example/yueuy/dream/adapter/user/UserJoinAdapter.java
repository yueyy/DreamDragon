package com.example.yueuy.dream.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.user.UserJoin;
import com.example.yueuy.dream.ui.activity.StoryMainActivity;
import com.example.yueuy.dream.util.SharedPreferencesUtils;
import java.util.List;

/**
 * Created by yueuy on 18-2-5.
 */

public class UserJoinAdapter extends RecyclerView.Adapter<UserJoinAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<UserJoin.JoinBean> mList;

    public UserJoinAdapter(Context context,List<UserJoin.JoinBean> story){
        this.mContext = context;
        this.mList = story;
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.content.setText(mList.get(position).getStory());
        final int storyid = mList.get(position).getStoryid();
//              点击查看故事详情
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),StoryMainActivity.class);
                i.putExtra("storyid",storyid);
                v.getContext().startActivity(i);
            }
        });
//               目前后台未返回数据

//        viewHolder.like.setText(mList.get(position).getLikenum());
//        viewHolder.talking.setText(mList.get(position).getTalkingnum());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        TextView like,talking;
        LinearLayout item;

        public ViewHolder(View itemView){
            super(itemView);
            content = itemView.findViewById(R.id.story_content);
            like = itemView.findViewById(R.id.story_like);
            talking = itemView.findViewById(R.id.story_talking);
            item = itemView.findViewById(R.id.ll_story);
        }
    }
}
