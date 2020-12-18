package com.example.constellation.home;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.constellation.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    ViewPager guideVg;
    //存放三张图片
    int loads[] = {R.mipmap.loading1, R.mipmap.loading2, R.mipmap.loading3};
    List<ImageView> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideVg = findViewById(R.id.guide_vp);
        mDatas = new ArrayList<>();
        initPager();
    }

    private void initPager() {
        for (int i = 0; i < loads.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(loads[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);
            mDatas.add(imageView);
        }
    }
}