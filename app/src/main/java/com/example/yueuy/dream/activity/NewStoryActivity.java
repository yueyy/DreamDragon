package com.example.yueuy.dream.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yueuy.dream.R;

/**
 * Created by yueuy on 18-1-29.
 */

public class NewStoryActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTitle;
    private EditText edtContent;
    private Button btnCancel;
    private Button btnPublish;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.edt_title:

                break;
            case R.id.edt_password:

                break;
            case R.id.btn_cancel:

                break;
            case R.id.btn_publish:

                break;
            default:
                break;
        }
    }

}
