package com.example.homework_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startService;
    private Button stopService;
    private TextView textView;
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