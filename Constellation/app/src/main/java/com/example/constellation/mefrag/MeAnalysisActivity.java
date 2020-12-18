package com.example.constellation.mefrag;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.constellation.R;
import com.example.constellation.utils.LoadDataAsyncTask;
import com.example.constellation.utils.URLContent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MeAnalysisActivity extends AppCompatActivity implements View.OnClickListener, LoadDataAsyncTask.OnGetNetDataListener {
    ListView meLv;
    TextView nameTv;
    ImageView backIv;
    List<MeItemBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_analysis);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //获取网址
        String meURL = URLContent.getMeURL(name);
        initView(name);
        mDatas = new ArrayList<>();
        //获取网络请求
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
        task.execute(meURL);
    }

    private void initView(String name) {
        meLv = findViewById(R.id.meanalysis_lv);
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
    //获取数据成功时的方法
    @Override
    public void onSuccess(String json) {
        if (!TextUtils.isEmpty(json)){
            //数据解析
            MeBean meBean = new Gson().fromJson(json, MeBean.class);
            //为了显示在ListView上，重新整理数据为集合形式
            addDataToList(meBean);
            //设置适配器
            MeAnalysisAdapter adapter = new MeAnalysisAdapter(this, mDatas);
            meLv.setAdapter(adapter);
        }
    }

    /*整理数据到集合中*/
    private void addDataToList(MeBean meBean) {
        MeItemBean lib1 = new MeItemBean("今日日期", meBean.getDatetime(), Color.BLUE);
        MeItemBean lib2 = new MeItemBean("财富指数", meBean.getMoney(), Color.YELLOW);
        MeItemBean lib3 = new MeItemBean("健康指数", meBean.getHealth(), Color.GREEN);
        MeItemBean lib4 = new MeItemBean("恋爱指数", meBean.getLove(), Color.MAGENTA);
        MeItemBean lib5 = new MeItemBean("工作指数", meBean.getWork(), Color.RED);
        MeItemBean lib6 = new MeItemBean("幸运数字", String.valueOf(meBean.getNumber()), Color.BLUE);
        MeItemBean lib7 = new MeItemBean("运势总结", meBean.getSummary(), Color.YELLOW);
        mDatas.add(lib1);
        mDatas.add(lib2);
        mDatas.add(lib3);
        mDatas.add(lib4);
        mDatas.add(lib5);
        mDatas.add(lib6);
        mDatas.add(lib7);
    }
}