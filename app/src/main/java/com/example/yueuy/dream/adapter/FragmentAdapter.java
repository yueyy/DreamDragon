package com.example.yueuy.dream.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        this.mFragmentList = list;
    }

    @Override
    public Fragment getItem(int arg0){
        return mFragmentList.get(arg0);
    }

    @Override
    public int getCount(){
        return mFragmentList.size();
    }
}
