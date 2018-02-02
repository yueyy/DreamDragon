package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yueuy.dream.R;

import java.util.Comparator;
import java.util.List;

/**
 * Created by yueuy on 18-2-1.
 */

public class MainWordAdapter extends RecyclerView.Adapter<MainWordAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mList;

    public MainWordAdapter(Context context, List<Integer> list){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mList = list;
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main_word,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.page.setText(mList.get(position).toString());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button page;

        public ViewHolder(View itemView){
            super(itemView);
            page = itemView.findViewById(R.id.main_word_page);
        }
    }
}
