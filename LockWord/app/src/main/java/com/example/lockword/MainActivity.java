package com.example.lockword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //用来显示单词和音标
    private TextView timeText, dateText, wordText, englishText;
    //用来播放声音
    private ImageView playVoice;
    //用来显示时间
    private String month, day, way, hours, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}