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

/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerFragment extends Fragment {
    ImageView manIv, womanIv;
    Spinner manSp, womanSp;
    Button prizeBtn, analysisBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_partner, container, false);
        initView(view);
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
    }
}