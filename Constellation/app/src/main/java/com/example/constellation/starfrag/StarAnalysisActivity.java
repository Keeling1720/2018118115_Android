package com.example.constellation.starfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener{
    TextView titleTv;
    ImageView backIv;
    CircleImageView iconIv;
    TextView nameTv, dateTv;
    ListView analysisLv;
    StarBean.StarinfoBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        //获取上级页面传过来的数据
        Intent intent = getIntent();
        bean = (StarBean.StarinfoBean) intent.getSerializableExtra("star");
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        titleTv = findViewById(R.id.title_tv);
        backIv = findViewById(R.id.title_iv_back);
        iconIv = findViewById(R.id.staranalysis_iv);
        nameTv = findViewById(R.id.staranalysis_tv_name);
        dateTv = findViewById(R.id.staranalysis_tv_date);
        analysisLv = findViewById(R.id.staranalysis_lv);

        titleTv.setText("星座详情");
        backIv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}