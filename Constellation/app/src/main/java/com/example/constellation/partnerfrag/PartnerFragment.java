package com.example.constellation.partnerfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerFragment extends Fragment implements View.OnClickListener{
    ImageView manIv, womanIv;
    Spinner manSp, womanSp;
    Button prizeBtn, analysisBtn;
    private List<StarBean.StarinfoBean> infoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_partner, container, false);
        initView(view);
        //获取Activitu传过来的数据
        Bundle bundle = getArguments();
        StarBean starBean = (StarBean) bundle.getSerializable("info");
        infoList =  starBean.getStarinfo();

        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        manIv = view.findViewById(R.id.partnerfrag_iv_man);
        womanIv = view.findViewById(R.id.partnerfrag_iv_woman);
        manSp = view.findViewById(R.id.partnerfrag_sp_man);
        womanSp = view.findViewById(R.id.partnerfrag_sp_woman);
        prizeBtn = view.findViewById(R.id.partnerfrag_btn_prize);
        analysisBtn = view.findViewById(R.id.partnerfrag_btn_analysis);
        //设置按钮的监听器
        prizeBtn.setOnClickListener(this);
        analysisBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.partnerfrag_btn_prize:

                break;
            case R.id.partnerfrag_btn_analysis:

                break;
        }
    }
}