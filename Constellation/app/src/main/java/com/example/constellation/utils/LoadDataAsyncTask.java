package com.example.constellation.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class LoadDataAsyncTask extends AsyncTask<String, Void, String> {
    Context context;
    OnGetNetDataListener listener;
    ProgressDialog dialog;
    boolean isShowDialog = false;

    private void initDialog(){
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中......");
    }

    public LoadDataAsyncTask(Context context, OnGetNetDataListener listener,boolean isShowDialog){
        this.context = context;
        this.listener = listener;
        this.isShowDialog = isShowDialog;
        initDialog();
    }

    public interface OnGetNetDataListener{
        public void onSuccess(String json);
    }

    //运行于主线程，常用于控件初始化
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isShowDialog){
            dialog.show();
        }

    }
    //运行于子线程，可在此处进行耗时操作（如获取网络请求）
    @Override
    protected String doInBackground(String... params) {
        String json = HttpUtils.getJSONFromNet(params[0]);
        return json;
    }
    //运行于主线程，可在此处得到doInBackground返回的数据，常用于更新控件更新
    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        if (isShowDialog){
            dialog.dismiss();
        }
        listener.onSuccess(json);
    }
}
