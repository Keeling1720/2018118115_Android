package com.example.constellation.mefrag;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.constellation.R;

import java.util.List;

public class MeAnalysisAdapter extends BaseAdapter {
    Context context;
    List<MeItemBean> mDatas;

    public MeAnalysisAdapter(Context context, List<MeItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MeAnalysisAdapter.ViewHolder holder = null;
        if (holder == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_me_analysis_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MeAnalysisAdapter.ViewHolder) convertView.getTag();
        }
        MeItemBean itemBean = mDatas.get(position);
        holder.titleTv.setText(itemBean.getTitle());
        holder.contentTv.setText(itemBean.getContent());
        //改变TextView的背景颜色
        GradientDrawable drawable = (GradientDrawable) holder.titleTv.getBackground();
        drawable.setColor(itemBean.getColorId());
        return convertView;
    }

    class ViewHolder{
        TextView titleTv, contentTv;
        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.item_meanalysis_tv_title);
            contentTv = view.findViewById(R.id.item_meanalysis_tv_content);
        }
    }
}
