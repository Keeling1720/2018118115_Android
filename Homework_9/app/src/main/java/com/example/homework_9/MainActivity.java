package com.example.homework_9;

import androidx.appcompat.app.AppCompatActivity;

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

}