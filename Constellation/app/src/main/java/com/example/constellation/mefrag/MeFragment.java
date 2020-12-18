package com.example.constellation.mefrag;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.constellation.R;
import com.example.constellation.bean.StarBean;
import com.example.constellation.luckfrag.LuckBaseAdapter;
import com.example.constellation.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    CircleImageView iconIv;
    TextView nameTv;
    LinearLayout appLayout,functionLayout,updateLayout,todayFortuneLayout;

    private Map<String, Bitmap> contentlogoImgMap;
    private List<StarBean.StarinfoBean> mDatas;
    private SharedPreferences star_pref;
    //保存选择的星座位置
    int selPostion = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取上个界面的数据
        Bundle bundle = getArguments();
        StarBean bean = (StarBean) bundle.getSerializable("info");
        mDatas = bean.getStarinfo();
        star_pref = getContext().getSharedPreferences("star_pref", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        iconIv = view.findViewById(R.id.mefrag_iv);
        nameTv = view.findViewById(R.id.mefrag_tv_name);
        iconIv.setOnClickListener(this);
        //进行初始化设置
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        //读取共享参数当中保存的星座名称和Logo名称
        String name = star_pref.getString("name", "白羊座");
        String logoname = star_pref.getString("logoname","baiyang");
        Bitmap bitmap = contentlogoImgMap.get(logoname);
        iconIv.setImageBitmap(bitmap);
        nameTv.setText(name);
        //初始化关于布局并设置监听
        appLayout = view.findViewById(R.id.app_layout);
        appLayout.setOnClickListener(this);
        //初始化功能介绍布局并设置监听
        functionLayout = view.findViewById(R.id.function_layout);
        functionLayout.setOnClickListener(this);
        //初始化更新日志布局并设置监听
        updateLayout = view.findViewById(R.id.update_layout);
        updateLayout.setOnClickListener(this);
        //初始化今日运势布局并设置监听
        todayFortuneLayout = view.findViewById(R.id.today_fortune_layout);
        todayFortuneLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mefrag_iv:
                showDialog();
                break;
            case R.id.app_layout:
                showAppDialog();
                break;
        }
    }
    //点击关于应用显示的会话框
    private void showAppDialog() {
        final Dialog dialog = new Dialog(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.me_app_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setTitle("关于应用");
        appLayout = dialogView.findViewById(R.id.app_layout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //点击星座Logo按钮显示会话框
    private void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.me_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setTitle("请选择您的星座");
        GridView dialogGv = dialogView.findViewById(R.id.mefrag_dialog_gv);
        //设置适配器
        LuckBaseAdapter adapter = new LuckBaseAdapter(getContext(), mDatas);
        dialogGv.setAdapter(adapter);
        //设置dialog可取消
        dialog.setCancelable(true);
        //设置点击弹出框的外部，可以取消弹出框
        dialog.setCanceledOnTouchOutside(true);

        //设置Gridview每一项的点击事件
        dialogGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarBean.StarinfoBean bean = mDatas.get(position);
                String name = bean.getName();
                String logoname = bean.getLogoname();
                nameTv.setText(name);
                Bitmap bitmap = contentlogoImgMap.get(logoname);
                iconIv.setImageBitmap(bitmap);
                selPostion = position;      //保存选择的位置
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        StarBean.StarinfoBean bean = mDatas.get(selPostion);
        String name = bean.getName();
        String logoname = bean.getLogoname();
        SharedPreferences.Editor editor = star_pref.edit();
        editor.putString("name", name);
        editor.putString("logoname", logoname);
        editor.commit();
    }
}