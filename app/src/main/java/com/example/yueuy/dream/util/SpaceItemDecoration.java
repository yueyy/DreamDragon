package com.example.yueuy.dream.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yueuy on 18-2-1.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,RecyclerView parent,RecyclerView.State state){
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
    }
}
