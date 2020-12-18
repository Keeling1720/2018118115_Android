package com.example.constellation.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.constellation.MainActivity;
import com.example.constellation.R;

public class WelcomeActivity extends AppCompatActivity {
    TextView tv;
    int count = 5;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1){
                count--;
                if (count == 0){
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    tv.setText(String.valueOf(count));
                    handler.sendEmptyMessageDelayed(1, 500);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);
        tv = findViewById(R.id.welcome_tv);
        handler.sendEmptyMessageDelayed(1, 500);
    }
}