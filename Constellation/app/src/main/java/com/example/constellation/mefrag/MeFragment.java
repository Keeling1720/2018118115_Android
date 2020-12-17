package com.example.constellation.mefrag;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
    private Map<String, Bitmap> contentlogoImgMap;
    private List<StarBean.StarinfoBean> mDatas;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取上个界面的数据
        Bundle bundle = getArguments();
        StarBean bean = (StarBean) bundle.getSerializable("info");
        mDatas = bean.getStarinfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        iconIv = view.findViewById(R.id.mefrag_iv);
        nameTv = view.findViewById(R.id.mefrag_tv_name);
        //进行初始化设置
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
        Bitmap bitmap = contentlogoImgMap.get("baiyang");
        iconIv.setImageBitmap(bitmap);
        iconIv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mefrag_iv:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        Dialog dialog = new Dialog(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.me_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setTitle("请选择您的星座");
        GridView dialogGv = dialogView.findViewById(R.id.mefrag_dialog_gv);
        //设置适配器
        LuckBaseAdapter adapter = new LuckBaseAdapter(getContext(), mDatas);
        dialogGv.setAdapter(adapter);

    }
}