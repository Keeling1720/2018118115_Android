package com.example.homework_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startService;
    private Button stopService;
    public static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainService", "onCreate被执行"+"此时线程id为"+Thread.currentThread().getId());
        startService = findViewById(R.id.start_service);
        stopService = findViewById(R.id.stop_service);
        textView = findViewById(R.id.text_content);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        textView.setText("当前线程为"+Thread.currentThread().getId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent start_intent = new Intent(this, MyService.class);
                startService(start_intent);
                break;
            case R.id.stop_service:
                Intent stop_intent = new Intent(this, MyService.class);
                stopService(stop_intent);
                break;
            default:
                break;
        }
    }
}

class Task extends AsyncTask<Integer, Integer, Void>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... params) {
        try {
                publishProgress(params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        MainActivity.textView.setText("线程"+values[0]+"\n"+
                "第"+values[1]+"次运行");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}