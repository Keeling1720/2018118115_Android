package com.example.constellation.luckfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.constellation.R;
import com.example.constellation.utils.URLContent;

public class LuckAnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck_analysis);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //获取网址
        String luckURL = URLContent.getLuckURL(name);

    }
}