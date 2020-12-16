package com.example.constellation.partnerfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.utils.AssetsUtils;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartnerAnalysisActivity extends AppCompatActivity {
    TextView manTv, womanTv, pdTv, vsTv, pfTv, jxTv, zyTv, titleTv;
    CircleImageView manIv, womanIv;
    ImageView backIv;
    private String man_name, woman_name;
    private String man_logoname, woman_logoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_analysis);
        initView();
        //获取上个界面传过来的数据
        getLastData();
    }

    private void getLastData() {
        Intent intent = getIntent();
        man_name = intent.getStringExtra("man_name");
        woman_name = intent.getStringExtra("woman_name");
        man_logoname = intent.getStringExtra("man_logoname");
        woman_logoname = intent.getStringExtra("woman_logoname");
        //设置能够显示的控件信息
        Map<String, Bitmap> contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap man_bitmap = contentlogoImgMap.get(man_logoname);
        manIv.setImageBitmap(man_bitmap);
        Bitmap woman_bitmap = contentlogoImgMap.get(woman_logoname);
        womanIv.setImageBitmap(woman_bitmap);

        manTv.setText(man_name);
        womanTv.setText(woman_name);
        pdTv.setText("星座配对- "+man_name+"和"+woman_name+"配对");
        vsTv.setText(man_name + " vs "+ woman_name);
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
        backIv = findViewById(R.id.title_iv_back);

    }
}