package com.example.constellation.luckfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.utils.URLContent;

public class LuckAnalysisActivity extends AppCompatActivity implements View.OnClickListener {
    ListView luckLv;
    TextView nameTv;
    ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_analysis);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //获取网址
        String luckURL = URLContent.getLuckURL(name);
        initView(name);
        //
    }

    private void initView(String name){
        //初始化控件
        luckLv = findViewById(R.id.luckanalysis_lv);
        nameTv = findViewById(R.id.title_tv);
        backIv = findViewById(R.id.title_iv_back);
        nameTv.setText(name);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_iv_back:
                finish();
                break;
        }
    }
}