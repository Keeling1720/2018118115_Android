package com.example.constellation.starfrag;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;

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
    private List<StarBean.StarinfoBean> mDatas;
    //声明图片数组
    int[] imgIds= {R.mipmap.pic_one, R.mipmap.pic_two, R.mipmap.pic_three, R.mipmap.pic_four, R.mipmap.pic_five};
    //声明ViewPager的数据源
    List<ImageView> ivList;
    //声明管理指示器小圆点的集合
    List<ImageView> pointList;
    StarPagerAdapter starPagerAdapter;
    //完成定时装置，实现自动滑动
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 1){
                //获取当前Viewpager显示的页面
                int currentItem = starVp.getCurrentItem();
                //判断是否为最后一张，如果是最后一张回到第一张，否者显示下一张
                if(currentItem == ivList.size() - 1){
                    starVp.setCurrentItem(0);
                }else {
                     currentItem++;
                     starVp.setCurrentItem(currentItem);
                }
                //形成循环发送，接受消息的效果，在接受消息的同时，也进行消息发送
                handler.sendEmptyMessageDelayed(1, 5000);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_star, container, false);
        initView(view);
        Bundle bundle = getArguments();
        StarBean infoBean = (StarBean) bundle.getSerializable("info");
        mDatas = infoBean.getStarinfo();    //获取关于星座信息的集合数据
        //创建适配器
        StarBaseAdapter starBaseAdapter = new StarBaseAdapter(getContext(), mDatas);
        starGv.setAdapter(starBaseAdapter);
        initPager();
        setVPListener();
        setGVListener();
        //每五秒发送一次通知，切换图片
        handler.sendEmptyMessageDelayed(1,5000);
        return view;
    }

    /**
     * 设置GridView的监听函数
     */
    private void setGVListener() {
        starGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarBean.StarinfoBean bean =  mDatas.get(position);
                Intent intent = new Intent(getContext(), StarAnalysisActivity.class);
                intent.putExtra("star", bean);
                startActivity(intent);
            }
        });
    }

    /**
     * 设置viewpager的监听器函数
     */
    private void setVPListener() {
        starVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i < pointList.size(); i++){
                    pointList.get(i).setImageResource(R.mipmap.point_normal);
                }
                pointList.get(position).setImageResource(R.mipmap.point_focus);
            }
        });
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