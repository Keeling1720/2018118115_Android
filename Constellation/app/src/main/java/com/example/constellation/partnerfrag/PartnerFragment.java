package com.example.constellation.partnerfrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;
import com.example.constellation.utils.AssetsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    ImageView manIv, womanIv;
    Spinner manSp, womanSp;
    Button prizeBtn, analysisBtn;
    private List<StarBean.StarinfoBean> infoList;
    List<String> nameList;      //存放星座名称的集合
    private Map<String, Bitmap> logoImgMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_partner, container, false);
        initView(view);
        //获取Activitu传过来的数据
        Bundle bundle = getArguments();
        StarBean starBean = (StarBean) bundle.getSerializable("info");
        infoList =  starBean.getStarinfo();
        nameList = new ArrayList<>();
        //获取适配器所需要的数据源
        for (int i = 0; i < infoList.size(); i++){
            String name = infoList.get(i).getName();
            nameList.add(name);
        }
        //创建适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_partner_sp,R.id.item_partner_tv, nameList);
        //设置适配器
        manSp.setAdapter(adapter);
        womanSp.setAdapter(adapter);

        //获取第一个图标显示在imageview
        String logoname = infoList.get(0).getLogoname();
        logoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap = logoImgMap.get(logoname);
        manIv.setImageBitmap(bitmap);
        womanIv.setImageBitmap(bitmap);
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
        manSp.setOnItemSelectedListener(this);
        womanSp.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.partnerfrag_btn_prize:

                break;
            case R.id.partnerfrag_btn_analysis:
                //获取Spanner选中的位置
                int manSelPos = manSp.getSelectedItemPosition();
                int womanSelPos = womanSp.getSelectedItemPosition();
                //跳转，传到星座配对详情界面
                Intent intent = new Intent(getContext(), PartnerAnalysisActivity.class);
                intent.putExtra("man_name", infoList.get(manSelPos).getName());
                intent.putExtra("man_logoname", infoList.get(manSelPos).getLogoname());
                intent.putExtra("woman_name", infoList.get(womanSelPos).getName());
                intent.putExtra("woman_name", infoList.get(womanSelPos).getLogoname());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.partnerfrag_sp_man:
                String logoname = infoList.get(position).getLogoname();
                Bitmap bitmap = logoImgMap.get(logoname);
                manIv.setImageBitmap(bitmap);
                break;
            case R.id.partnerfrag_sp_woman:
                logoname = infoList.get(position).getLogoname();
                bitmap = logoImgMap.get(logoname);
                womanIv.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}