package com.example.constellation.partnerfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.constellation.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerAnalysisActivity extends AppCompatActivity {
    TextView manTv, womanTv, pdTv, vsTv, pfTv, jxTv, zyTv, titleTv;
    CircleImageView manIv, womanIv;
    ImageView backIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_analysis);
        initView();
    }

    private void initView() {
        manIv = findViewById(R.id.partneranalysis_iv_man);
        womanIv = findViewById(R.id.partneranalysis_iv_woman);
        manTv = findViewById(R.id.partneranalysis_tv_man);
        womanTv = findViewById(R.id.partneranalysis_tv_woman);
        pdTv = findViewById(R.id.prtneranalysis_tv_pd);
        vsTv = findViewById(R.id.prtneranalysis_tv_vs);
        pfTv = findViewById(R.id.prtneranalysis_tv_pf);
        jxTv = findViewById(R.id.prtneranalysis_tv_jx);
        zyTv = findViewById(R.id.prtneranalysis_tv_zy);
        titleTv = findViewById(R.id.title_tv);

    }
}