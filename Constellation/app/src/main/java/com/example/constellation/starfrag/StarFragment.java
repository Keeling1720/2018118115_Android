package com.example.constellation.starfrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.constellation.R;
import com.example.constellation.bean.StarInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 星座Fragment
 *      包括ViewPager和GridView
 */
public class StarFragment extends Fragment {
    ViewPager starVp;
    GridView starGv;
    LinearLayout pointLayout;
    private List<StarInfoBean.StarinfoBean> mDatas;
    //声明图片数组
    int[] imgIds= {R.mipmap.pic_guanggao, R.mipmap.pic_star};
    //声明ViewPager的数据源
    List<ImageView> ivList;
    //声明管理指示器小圆点的集合
    List<ImageView> pointList;
    StarPagerAdapter starPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        initView(view);
        Bundle bundle = getArguments();
        StarInfoBean infoBean = (StarInfoBean) bundle.getSerializable("info");
        mDatas = infoBean.getStarinfo();    //获取关于星座信息的集合数据
        //创建适配器
        StarBaseAdapter starBaseAdapter = new StarBaseAdapter(getContext(), mDatas);
        starGv.setAdapter(starBaseAdapter);
        initPager();
        return view;
    }

    /**
     * 设置ViewPager显示的页面
     */
    private void initPager() {
        ivList = new ArrayList<>();
        pointList = new ArrayList<>();
        for (int i = 0; i < imgIds.length ; i++){
            ImageView iv = new ImageView(getContext());
            iv.setImageResource(imgIds[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //设置图片的宽高
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            //将图片view加载到集合当中
            ivList.add(iv);
            //创建图片对应的指示器小圆点
            ImageView piv =  new ImageView(getContext());
            piv.setImageResource(R.mipmap.point_normal);
            LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            plp.setMargins(20,0,0,0);
            piv.setLayoutParams(plp);
            //将小圆点添加到布局
            pointLayout.addView(piv);
            //将小圆点加入统一管理的集合中
            pointList.add(piv);
        }
        //默认第一个小圆点是获取焦点状态
        pointList.get(0).setImageResource(R.mipmap.point_focus);
        starPagerAdapter = new StarPagerAdapter(getContext(), ivList);
        starVp.setAdapter(starPagerAdapter);
    }

    /* 初始化控件操作*/
    private void initView(View view) {
        starVp = view.findViewById(R.id.starfrag_vp);
        starGv = view.findViewById(R.id.starfrag_gv);
        pointLayout = view.findViewById(R.id.starfrag_layout);
    }
}