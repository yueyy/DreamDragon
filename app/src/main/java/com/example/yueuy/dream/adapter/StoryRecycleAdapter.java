package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yueuy.dream.R;
import com.example.yueuy.dream.data.story.StoryLike;
import com.example.yueuy.dream.data.story.StoryRandom;
import com.example.yueuy.dream.net.ServiceGenerator;
import com.example.yueuy.dream.net.api.StoryService;
import com.example.yueuy.dream.ui.activity.StoryMainActivity;
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
    private List<StoryRandom.RandomBean> mStoryData;
    private LayoutInflater mInflater;
    boolean clickLike = false;
    private SharedPreferencesUtils mPreferencesUtils = new SharedPreferencesUtils();

    public StoryRecycleAdapter(Context context, List<StoryRandom.RandomBean> storyData){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mStoryData = storyData;

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
        if (mStoryData.isEmpty()){
            viewHolder.content.setText("");
            viewHolder.likenum.setText("");
            viewHolder.keyword.setText("");
        }else {
            viewHolder.content.setText(mStoryData.get(position).getStory());
            viewHolder.likenum.setText((mStoryData.get(position).getLikenum()+""));
            viewHolder.keyword.setText(mStoryData.get(position).getKeyword()
                    .get(position).getKeyword());

            //      点击查看故事详情
            final int storyid = mStoryData.get(position).getStoryid();
            viewHolder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),StoryMainActivity.class);
                    i.putExtra("storyid",storyid);
                    v.getContext().startActivity(i);
                }
            });
            //       点赞
            viewHolder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    String token = mPreferencesUtils.getUser("token");
                    if (token!=null||storyid != 0){
                        StoryService storyService = ServiceGenerator.createService(StoryService.class);
                        storyService.like(storyid,token).enqueue(new Callback<StoryLike>() {
                            @Override
                            public void onResponse(Call<StoryLike> call, Response<StoryLike> response) {
                                try {
                                    viewHolder.like.setColorFilter(Color.RED);
                                    viewHolder.likenum.setText(String.valueOf(response.body().getLikenum()));
                                    Toast.makeText(v.getContext(),"点赞成功",Toast.LENGTH_SHORT).show();
                                }catch (NullPointerException e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<StoryLike> call, Throwable t) {
                                Toast.makeText(v.getContext(),"点赞失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(v.getContext(),"点赞失败,请先登录",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView content,keyword,likenum,talkingnum;
        ImageButton like,talking;


        public ViewHolder(View itemView){
            super(itemView);
            content = itemView.findViewById(R.id.random_content);
            keyword = itemView.findViewById(R.id.random_keyword);
            likenum = itemView.findViewById(R.id.random_like);
            talkingnum = itemView.findViewById(R.id.random_talking);
            like = itemView.findViewById(R.id.random_like_img);
            talking = itemView.findViewById(R.id.random_talking_img);
        }
    }
}
