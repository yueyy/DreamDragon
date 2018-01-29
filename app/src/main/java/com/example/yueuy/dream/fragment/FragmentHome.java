package com.example.yueuy.dream.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yueuy.dream.R;

import retrofit2.http.Path;

/**
 * Created by yueuy on 18-1-29.
 */

public class FragmentHome extends Fragment {


    private TextView mTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page,null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mTextView = getActivity().findViewById(R.id.tv_home);
    }

}
