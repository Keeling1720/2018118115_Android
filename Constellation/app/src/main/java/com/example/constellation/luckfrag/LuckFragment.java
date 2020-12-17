package com.example.constellation.luckfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

import java.io.Serializable;
import java.util.List;

/**
 * 运势界面
 */
public class LuckFragment extends Fragment {
    GridView luckGv;
    List<StarBean.StarinfoBean> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luck, container, false);
        luckGv = view.findViewById(R.id.luckfrag_gv);
        //获取数据源
        Bundle bundle = getArguments();
        StarBean info = (StarBean) bundle.getSerializable("info");
        mDatas = info.getStarinfo();

        return view;
    }
}