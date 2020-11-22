package com.example.homework_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button download;
    private TextView textContent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWindow();
    }

    private void initWindow(){
        this.download = (Button) findViewById(R.id.download);
        this.textContent = (TextView) findViewById(R.id.text_info);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    class DownloadTask extends AsyncTask<Integer, Integer, String>{
        //第一个执行方法，后台任务开始执行前调用
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //第二个方法，在子线程中运行，在doPreExecute方法执行完后运行
        @Override
        protected String doInBackground(Integer... params) {
            for(int i = 0; i < 100; i++){
                progressBar.setProgress(i);
                publishProgress(i);
                //每次调用都再开辟一个子线程休息params[0]时间
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } ;
            }
            return "Success";
        }

        //第三个方法，每次调用publishProgress方法时调用，传过来的参数在values中接收
        //该方法运行在主线程，可以对界面进行修改
        @Override
        protected void onProgressUpdate(Integer... values) {
            textContent.setText(values[0]+"%");
            super.onProgressUpdate(values);
        }

        //doInBackground结束时触发，result为doInBackground的return值
        @Override
        protected void onPostExecute(String result) {
            setTitle(result);
            super.onPostExecute(result);
        }
    }
}