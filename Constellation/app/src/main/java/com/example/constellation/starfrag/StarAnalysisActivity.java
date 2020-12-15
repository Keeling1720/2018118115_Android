package com.example.constellation.starfrag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;
import com.example.constellation.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class StarAnalysisActivity extends AppCompatActivity implements View.OnClickListener{
    TextView titleTv;
    ImageView backIv;
    CircleImageView iconIv;
    TextView nameTv, dateTv;
    ListView analysisLv;
    StarBean.StarinfoBean bean;
    private Map<String, Bitmap> contentlogoImgMap;
    private TextView footerTv;
    private AnalysisBaseAdapter adapter;
    List<StarAnalysisBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_analysis);
        //获取上级页面传过来的数据
        Intent intent = getIntent();
        bean = (StarBean.StarinfoBean) intent.getSerializableExtra("star");
        initView();
        mDatas = new ArrayList<>(); //初始化显示在ListView上的数据源
        adapter = new AnalysisBaseAdapter(this, mDatas);
        analysisLv.setAdapter(adapter);
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
        //为ListView添加底部布局
        View footerView = LayoutInflater.from(this).inflate(R.layout.footer_star_analysis, null);
        analysisLv.addFooterView(footerView);
        footerTv = footerView.findViewById(R.id.footerstar_tv_info);
        //将数据进行显示
        titleTv.setText("星座详情");
        backIv.setOnClickListener(this);
        nameTv.setText(bean.getName());
        dateTv.setText(bean.getDate());
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap = contentlogoImgMap.get(bean.getLogoname());
        iconIv.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}