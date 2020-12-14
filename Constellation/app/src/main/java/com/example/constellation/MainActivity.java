package com.example.constellation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup mainRg;
    //声明四个按钮的Fragment对象
    private Fragment starFrag, luckFrag, partnerFrag, meFrag;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }

    private void initWidget(){
        mainRg = (RadioGroup) findViewById(R.id.main_rg);
        //设置监听点击哪个RadioButton
        mainRg.setOnCheckedChangeListener(this);
        //创建碎片对象
        starFrag = new StarFragment();
        luckFrag = new LuckFragment();
        partnerFrag = new PartnerFragment();
        meFrag = new MeFragment();
        //将四个Fragment进行动态加载
        addFragment();
    }

    /**
     * @des 将主页当中的碎片一起加载到布局，有用的显示，无用的隐藏
     */
    private void addFragment() {
        //创建碎片管理者对象
        manager = getSupportFragmentManager();
        //创建碎片处理事务对象
        FragmentTransaction transaction = manager.beginTransaction();
        //将四个Fragment统一添加到布局
        transaction.add(R.id.main_layout_center, this.starFrag);
        transaction.add(R.id.main_layout_center, this.partnerFrag);
        transaction.add(R.id.main_layout_center, this.luckFrag);
        transaction.add(R.id.main_layout_center, this.meFrag);
        //隐藏后面的三个
        transaction.hide(partnerFrag);
        transaction.hide(luckFrag);
        transaction.hide(meFrag);
        //提交碎片改变后的事务
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_rb_star:

                break;
            case R.id.main_rb_parent:

                break;
            case R.id.main_rb_luck:

                break;
            case R.id.main_rb_me:

                break;
        }
    }
}